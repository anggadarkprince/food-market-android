package com.anggaari.foodmarket

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.multidex.MultiDexApplication
import com.anggaari.foodmarket.network.HttpClient

class FoodMarket: MultiDexApplication() {
    companion object {
        lateinit var instance: FoodMarket
        fun getApp(): FoodMarket {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token: String) {
        getPreferences().edit().putString("token", token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken(): String? {
        return getPreferences().getString("token", null)
    }

    fun setUser(user: String) {
        getPreferences().edit().putString("user", user).apply()
        HttpClient.getInstance().buildRetrofitClient(user)
    }

    fun getUser(): String? {
        return getPreferences().getString("user", null)
    }
}