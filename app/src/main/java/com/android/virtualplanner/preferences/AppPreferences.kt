package com.android.virtualplanner.preferences

import android.content.ContentProviderOperation
import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "VirtualPlannerApp"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val IS_LOGGED_IN = Pair("is_logged_in", false)
    private val USERNAME = Pair("username", "")

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    //an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isLoggedIn: Boolean
        get() = preferences.getBoolean(IS_LOGGED_IN.first, IS_LOGGED_IN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGGED_IN.first, value)
        }

    var username: String
        get() = preferences.getString(USERNAME.first, USERNAME.second) ?: ""
        set(value) = preferences.edit {
            it.putString(USERNAME.first, value)
        }
}