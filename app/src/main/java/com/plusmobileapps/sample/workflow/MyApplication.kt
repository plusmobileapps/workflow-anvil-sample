package com.plusmobileapps.sample.workflow

import android.app.Application
import com.plusmobileapps.rickandmortysdk.RickAndMorty
import com.plusmobileapps.sample.workflow.di.AppComponent
import com.plusmobileapps.sample.workflow.di.AppModule
import com.plusmobileapps.sample.workflow.di.DaggerAppComponent

class MyApplication : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        RickAndMorty.init(this)
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}