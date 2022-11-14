package ru.mtrefelov.dialer.app

import android.app.Application

import ru.mtrefelov.dialer.BuildConfig
import ru.mtrefelov.dialer.app.di.DependencyContainer

import timber.log.Timber

class DialerApplication : Application() {
    lateinit var dependencyContainer: DependencyContainer

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        dependencyContainer = DependencyContainer(applicationContext)
    }
}