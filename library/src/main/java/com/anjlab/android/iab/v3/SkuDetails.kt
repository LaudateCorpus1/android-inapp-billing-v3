/**
 * Copyright 2014 AnjLab
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anjlab.android.iab.v3

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import org.json.JSONException
import org.json.JSONObject
import java.util.*

data class SkuDetails(val productId: String?,
                      val title: String,
                      val description: String,
                      val isSubscription: Boolean,
                      val currency: String,
                      val priceLong: Long,
                      val subscriptionPeriod: String,
                      val subscriptionFreeTrialPeriod: String,
                      val introductoryPricePeriod: String,
                      val introductoryPriceCycles: Int,
                      val priceText: String,
                      val introductoryPriceLong: Long,
                      val introductoryPriceText: String) : Parcelable {

    val priceValue: Double = priceLong / 1000000.0
    val haveTrialPeriod: Boolean = !TextUtils.isEmpty(subscriptionFreeTrialPeriod)
    val introductoryPriceValue: Double = introductoryPriceLong / 1000000.0
    val haveIntroductoryPeriod: Boolean = !TextUtils.isEmpty(introductoryPricePeriod)

    /**
     * Use this value to return the raw price from the product.
     * This allows math to be performed without needing to worry about errors
     * caused by floating point representations of the product's price.
     *
     *
     * This is in micros from the Play Store.
     */
    //    val priceLong: Long

    @Throws(JSONException::class)
    constructor(source: JSONObject) : this(
            source.optString(Constants.RESPONSE_PRODUCT_ID),
            source.optString(Constants.RESPONSE_TITLE),
            source.optString(Constants.RESPONSE_DESCRIPTION),
            isSub(source.optString(Constants.RESPONSE_TYPE)),
            source.optString(Constants.RESPONSE_PRICE_CURRENCY),
            source.optLong(Constants.RESPONSE_PRICE_MICROS),
            source.optString(Constants.RESPONSE_SUBSCRIPTION_PERIOD),
            source.optString(Constants.RESPONSE_FREE_TRIAL_PERIOD),
            source.optString(Constants.RESPONSE_INTRODUCTORY_PRICE_PERIOD),
            source.optInt(Constants.RESPONSE_INTRODUCTORY_PRICE_CYCLES),
            source.optString(Constants.RESPONSE_PRICE),
            source.optLong(Constants.RESPONSE_INTRODUCTORY_PRICE_MICROS),
            source.optString(Constants.RESPONSE_INTRODUCTORY_PRICE)
    )

    override fun toString(): String {
        return String.format(Locale.US, "%s: %s(%s) %f in %s (%s)",
                productId,
                title,
                description,
                priceValue,
                currency,
                priceText)
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(productId)
        dest.writeString(title)
        dest.writeString(description)
        dest.writeByte(if (isSubscription) 1.toByte() else 0.toByte())
        dest.writeString(currency)
        dest.writeLong(priceLong)
        dest.writeString(subscriptionPeriod)
        dest.writeString(subscriptionFreeTrialPeriod)
        dest.writeString(introductoryPricePeriod)
        dest.writeInt(introductoryPriceCycles)
        dest.writeString(priceText)
        dest.writeLong(introductoryPriceLong)
        dest.writeString(introductoryPriceText)
    }

    protected constructor(inParcel: Parcel) : this(
            inParcel.readString(), // productId
            inParcel.readString(), // title
            inParcel.readString(), // description
            inParcel.readByte() != 0.toByte(), // isSubscription
            inParcel.readString(), // currency
            inParcel.readLong(), // priceLong
            inParcel.readString(), // subscriptionPeriod
            inParcel.readString(), // subscriptionFreeTrialPeriod
            inParcel.readString(), // introductoryPricePeriod
            inParcel.readInt(), // introductoryPriceCycles
            inParcel.readString(), // priceText
            inParcel.readLong(), // introductoryPriceLong
            inParcel.readString()  // introductoryPriceText
    )

    companion object {

        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<SkuDetails> = object : Parcelable.Creator<SkuDetails> {
            override fun createFromParcel(source: Parcel): SkuDetails {
                return SkuDetails(source)
            }

            override fun newArray(size: Int): Array<SkuDetails?> {
                return arrayOfNulls(size)
            }
        }
    }
}

fun isSub(responseType: String?) = responseType?.equals(Constants.PRODUCT_TYPE_SUBSCRIPTION, ignoreCase = true) ?: false