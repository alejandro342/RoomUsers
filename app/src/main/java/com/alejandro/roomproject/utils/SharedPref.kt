package com.alejandro.roomproject.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

class SharedPref(activity: Context) {
    private var prefs: SharedPreferences? = null

    init {
        prefs = activity.getSharedPreferences("com.alejandro.roomproject", Context.MODE_PRIVATE)
    }

    fun saveSession(key: String, objeto: Any) {
        try {
            val gson = Gson()
            val json = gson.toJson(objeto)
            with(prefs?.edit()) {
                this?.putString(key, json)
                this?.commit()
            }
        } catch (e: Exception) {
            Log.d("ERROR", "Err:${e.message}")
        }
    }

    fun getInformationSesion(key: String): String? {
        val informationSession = prefs?.getString(key, "")
        return informationSession
    }

    fun closeSession(key: String) {
        prefs?.edit()?.remove(key)?.apply()
    }
}














































