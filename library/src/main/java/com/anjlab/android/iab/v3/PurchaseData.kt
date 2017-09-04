/**
 * Copyright 2014 AnjLab
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
import java.util.*

data class PurchaseData(val orderId: String,
                        val packageName: String,
                        val productId: String,
                        val purchaseTime: Date?,
                        val purchaseState: PurchaseState?,
                        val developerPayload: String,
                        val purchaseToken: String,
                        val autoRenewing: Boolean) : Parcelable {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(orderId)
        dest.writeString(packageName)
        dest.writeString(productId)
        dest.writeLong(purchaseTime?.time ?: -1)
        dest.writeInt(purchaseState?.ordinal ?: -1)
        dest.writeString(developerPayload)
        dest.writeString(purchaseToken)
        dest.writeByte(if (autoRenewing) 1.toByte() else 0.toByte())
    }

    protected constructor(inParcel: Parcel) : this(
            inParcel.readString(),
            inParcel.readString(),
            inParcel.readString(),
            dateFromLong(inParcel.readLong()),
            purchaseStateFromInt(inParcel.readInt()),
            inParcel.readString(),
            inParcel.readString(),
            inParcel.readByte() != 0.toByte()
    )

    companion object {

        @JvmField
        @Suppress("unused")
        val CREATOR: Parcelable.Creator<PurchaseData> = object : Parcelable.Creator<PurchaseData> {
            override fun createFromParcel(source: Parcel): PurchaseData {
                return PurchaseData(source)
            }

            override fun newArray(size: Int): Array<PurchaseData?> {
                return arrayOfNulls(size)
            }
        }
    }
}

fun dateFromLong(purchaseTime: Long): Date? = if (purchaseTime != -1L) Date(purchaseTime) else null

fun purchaseStateFromInt(purchaseState: Int): PurchaseState? = if (purchaseState != -1) PurchaseState.values()[purchaseState] else null