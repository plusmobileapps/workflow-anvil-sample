package com.plusmobileapps.sample.workflow

import android.app.Application
import com.plusmobileapps.sample.workflow.di.AppComponent
import com.plusmobileapps.sample.workflow.di.DaggerAppComponent

class MyApplication : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}