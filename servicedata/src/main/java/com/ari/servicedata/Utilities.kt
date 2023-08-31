package com.ari.servicedata

import android.content.Context

object Utilities {

    private const val API_KEY = "fbb9572d11b5458ac98f02b84f2bafc4"

    fun getApiKey(context: Context): String? {
        val name = "${context.packageName}.$API_KEY"
        val preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return preferences.getString(API_KEY, null)
    }
}