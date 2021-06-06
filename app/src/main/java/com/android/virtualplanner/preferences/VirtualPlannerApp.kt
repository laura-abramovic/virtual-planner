package com.android.virtualplanner.preferences

import android.app.Application

class VirtualPlannerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}