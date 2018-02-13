@file:JvmName("Constants")

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

const val GOOGLE_API_VERSION = 3
const val GOOGLE_API_SUBSCRIPTION_CHANGE_VERSION = 5
const val GOOGLE_API_VR_SUPPORTED_VERSION = 7

const val PRODUCT_TYPE_MANAGED = "inapp"
const val PRODUCT_TYPE_SUBSCRIPTION = "subs"

//Success
const val BILLING_RESPONSE_RESULT_OK = 0

//User pressed back or canceled a dialog
const val BILLING_RESPONSE_RESULT_USER_CANCELED = 1

// Network connection is down
const val BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE = 2

//Billing API version is not supported for the type requested
const val BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3

//Requested product is not available for purchase
const val BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4

//Invalid arguments provided to the API. This error can also indicate that the application
// was not correctly signed or properly set up for In-app Billing in Google Play, or
// does not have the necessary permissions in its manifest
const val BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5

//Fatal error during the API action
const val BILLING_RESPONSE_RESULT_ERROR = 6

//Failure to purchase since item is already owned
const val BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7

//Failure to consume since item is not owned
const val BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8

const val RESPONSE_CODE = "RESPONSE_CODE"
const val DETAILS_LIST = "DETAILS_LIST"
const val PRODUCTS_LIST = "ITEM_ID_LIST"
const val BUY_INTENT = "BUY_INTENT"
const val INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST"
const val INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA"
const val RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE"
const val INAPP_DATA_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST"
const val RESPONSE_ORDER_ID = "orderId"
const val RESPONSE_PRODUCT_ID = "productId"
const val RESPONSE_PACKAGE_NAME = "packageName"
const val RESPONSE_PURCHASE_TIME = "purchaseTime"
const val RESPONSE_PURCHASE_STATE = "purchaseState"
const val RESPONSE_PURCHASE_TOKEN = "purchaseToken"
const val RESPONSE_DEVELOPER_PAYLOAD = "developerPayload"
const val RESPONSE_TYPE = "type"
const val RESPONSE_TITLE = "title"
const val RESPONSE_DESCRIPTION = "description"
const val RESPONSE_PRICE = "price"
const val RESPONSE_PRICE_CURRENCY = "price_currency_code"
const val RESPONSE_PRICE_MICROS = "price_amount_micros"
const val RESPONSE_SUBSCRIPTION_PERIOD = "subscriptionPeriod"
const val RESPONSE_AUTO_RENEWING = "autoRenewing"
const val RESPONSE_FREE_TRIAL_PERIOD = "freeTrialPeriod"
const val RESPONSE_INTRODUCTORY_PRICE = "introductoryPrice"
const val RESPONSE_INTRODUCTORY_PRICE_MICROS = "introductoryPriceAmountMicros"
const val RESPONSE_INTRODUCTORY_PRICE_PERIOD = "introductoryPricePeriod"
const val RESPONSE_INTRODUCTORY_PRICE_CYCLES = "introductoryPriceCycles"

const val BILLING_ERROR_FAILED_LOAD_PURCHASES = 100
const val BILLING_ERROR_FAILED_TO_INITIALIZE_PURCHASE = 101
const val BILLING_ERROR_INVALID_SIGNATURE = 102
const val BILLING_ERROR_LOST_CONTEXT = 103
const val BILLING_ERROR_INVALID_MERCHANT_ID = 104

@Deprecated("")
const val BILLING_ERROR_INVALID_DEVELOPER_PAYLOAD = 105
const val BILLING_ERROR_OTHER_ERROR = 110
const val BILLING_ERROR_CONSUME_FAILED = 111
const val BILLING_ERROR_SKUDETAILS_FAILED = 112
const val BILLING_ERROR_BIND_PLAY_STORE_FAILED = 113

const val EXTRA_PARAMS_KEY_VR = "vr"
const val EXTRA_PARAMS_KEY_SKU_TO_REPLACE = "skusToReplace"