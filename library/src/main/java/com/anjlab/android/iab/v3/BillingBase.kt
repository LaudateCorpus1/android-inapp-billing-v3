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

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

internal open class BillingBase(val context: Context) {

    val preferencesBaseKey: String
        get() = context.packageName + "_preferences"

    private val preferences: SharedPreferences?
        get() = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveString(key: String, value: String): Boolean {
        val sp = preferences
        if (sp != null) {
            sp.edit()
                    .putString(key, value)
                    .commit()
            return true
        }
        return false
    }

    fun loadString(key: String, defValue: String): String? =
            preferences?.getString(key, defValue) ?: defValue

    fun saveBoolean(key: String, value: Boolean): Boolean {
        val sp = preferences
        if (sp != null) {
            sp.edit()
                    .putBoolean(key, value)
                    .commit()
            return true
        }
        return false
    }

    fun loadBoolean(key: String, defValue: Boolean): Boolean =
            preferences?.getBoolean(key, defValue) ?: defValue
}