/**
 * Copyright 2014 AnjLab and Unic8
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.util.*

const val LOG_TAG = "iabv3.purchaseInfo"

/**
 * With this PurchaseInfo a developer is able verify a purchase from the google play store on his
 * own server. An example implementation of how to verify a purchase you can find
 * [here](https://github.com/mgoldsborough/google-play-in-app-billing-verification/blob/master/library/GooglePlay/InAppBilling/GooglePlayResponseValidator.php#L64)
 */
data class PurchaseInfo(val responseData: String,
                        val signature: String) : Parcelable {
    val purchaseData: PurchaseData?

    init {
        purchaseData = parseResponseDataImpl()
    }

    @Deprecated("don't call it directly, use {@see purchaseData} instead.", ReplaceWith("parseResponseDataImpl()"))
    fun parseResponseData(): PurchaseData? {
        return parseResponseDataImpl()
    }

    fun parseResponseDataImpl(): PurchaseData? {
        try {
            val json = JSONObject(responseData)

            val purchaseTimeMillis = json.optLong(Constants.RESPONSE_PURCHASE_TIME, 0)

            return PurchaseData(json.optString(Constants.RESPONSE_ORDER_ID),
                    json.optString(Constants.RESPONSE_ORDER_ID),
                    json.optString(Constants.RESPONSE_PRODUCT_ID),
                    if (purchaseTimeMillis != 0L) Date(purchaseTimeMillis) else null,
                    PurchaseState.values()[json.optInt(Constants.RESPONSE_PURCHASE_STATE, 1)],
                    json.optString(Constants.RESPONSE_DEVELOPER_PAYLOAD),
                    json.getString(Constants.RESPONSE_PURCHASE_TOKEN),
                    json.optBoolean(Constants.RESPONSE_AUTO_RENEWING)
            )
        } catch (e: JSONException) {
            Log.e(LOG_TAG, "Failed to parse response data", e)
            return null
        }

    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.responseData)
        dest.writeString(this.signature)
    }

    protected constructor(inParcel: Parcel) : this(inParcel.readString(), inParcel.readString())

    companion object {

        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<PurchaseInfo> = object : Parcelable.Creator<PurchaseInfo> {
            override fun createFromParcel(source: Parcel): PurchaseInfo {
                return PurchaseInfo(source)
            }

            override fun newArray(size: Int): Array<PurchaseInfo?> {
                return arrayOfNulls(size)
            }
        }
    }
}
