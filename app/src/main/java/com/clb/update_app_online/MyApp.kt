package com.clb.update_app_online

import android.app.Application

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        UpDateApp.init("ede71b84c7e1009fe6bdee737c7dfaf4","5885ac48608e2a0470266d3980484746")
    }
}