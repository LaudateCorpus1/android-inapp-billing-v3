/*
 * Copyright 2014 AnjLab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anjlab.android.iab.v3

import android.content.Context
import android.text.TextUtils
import java.util.*
import java.util.regex.Pattern

private const val ENTRY_DELIMITER = "#####"
private const val LINE_DELIMITER = ">>>>>"
private const val VERSION_KEY = ".version"

// TODO this class needs testing
internal class BillingCache(context: Context, private val cacheKey: String) : BillingBase(context) {

    private val data: HashMap<String, PurchaseInfo> = HashMap()
    private var version: String = ""

    init {
        load()
    }

    private val preferencesCacheKey: String
        get() = preferencesBaseKey + cacheKey

    private val preferencesVersionKey: String
        get() = preferencesCacheKey + VERSION_KEY

    private fun load() {
        loadString(preferencesCacheKey, "").split(Pattern.quote(ENTRY_DELIMITER).toRegex()) // TODO is toRegex() necessary?
                .filterNot { TextUtils.isEmpty(it) }
                .map { it.split(Pattern.quote(LINE_DELIMITER).toRegex()) } // TODO is toRegex() necessary?
                .filter { it.size > 1 }
                .forEach {
                    val signature = if (it.size > 2) it[2] else null
                    data.put(it[0], PurchaseInfo(it[1], signature))
                }
        version = currentVersion
    }

    private fun flush() {
        val output = ArrayList<String>()
        for (productId in data.keys) {
            val info = data[productId]
            output.add(productId + LINE_DELIMITER + info?.responseData + LINE_DELIMITER + // TODO nullability
                    info?.signature)
        }
        saveString(preferencesCacheKey, TextUtils.join(ENTRY_DELIMITER, output))
        version = Date().time.toString()
        saveString(preferencesVersionKey, version)
    }

    fun includesProduct(productId: String): Boolean {
        reloadDataIfNeeded()
        return data.containsKey(productId)
    }

    fun getDetails(productId: String): PurchaseInfo? {
        reloadDataIfNeeded()
        return if (data.containsKey(productId)) data[productId] else null
    }

    fun put(productId: String, details: String, signature: String) {
        reloadDataIfNeeded()
        if (!data.containsKey(productId)) {
            data.put(productId, PurchaseInfo(details, signature))
            flush()
        }
    }

    fun remove(productId: String) {
        reloadDataIfNeeded()
        if (data.containsKey(productId)) {
            data.remove(productId)
            flush()
        }
    }

    fun clear() {
        reloadDataIfNeeded()
        data.clear()
        flush()
    }

    private val currentVersion: String
        get() = loadString(preferencesVersionKey, "0")

    private fun reloadDataIfNeeded() {
        if (!version.equals(currentVersion, ignoreCase = true)) {
            data.clear()
            load()
        }
    }

    val contents: List<String>
        get() = ArrayList(data.keys)

    override fun toString(): String {
        return TextUtils.join(", ", data.keys)
    }
}