package com.mhp.showcase

import android.app.Application
import android.util.Log
import com.mhp.showcase.module.DaggerShowcaseComponent
import com.mhp.showcase.module.ShowcaseComponent

/**
 * The [Application] class for the app. Initializes dependency injection and provides an instance of
 * [android.content.Context]
 */
class ShowcaseApplication : Application() {
    private val TAG: String = ShowcaseApplication::class.java.simpleName

    companion object {
        lateinit var graph: ShowcaseComponent
        lateinit var instance: ShowcaseApplication
        fun getContext(): ShowcaseApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerShowcaseComponent.builder().build()
        Log.d(TAG, "created dependency injection component")
    }

    init {
        instance = this
    }
}
