package com.mhp.showcase

import android.app.Application
import android.util.Log
import com.mhp.showcase.module.DaggerShowcaseComponent
import com.mhp.showcase.module.ShowcaseComponent


class ShowcaseApplication : Application() {
    val TAG: String = ShowcaseApplication::class.java.simpleName

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: ShowcaseComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerShowcaseComponent.builder().build()
        Log.d(TAG, "created dependency injection component")
    }


}
