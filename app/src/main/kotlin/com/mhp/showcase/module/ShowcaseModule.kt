package com.mhp.showcase.module

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mhp.showcase.ShowcaseApplication
import com.mhp.showcase.block.BaseBlock
import com.mhp.showcase.model.view.HomeViewModel
import com.mhp.showcase.network.GetBlocksNetworkService
import com.mhp.showcase.util.BlockViewHelper
import com.mhp.showcase.util.Constants
import com.mhp.showcase.util.GsonBlockAdapter
import dagger.Module
import dagger.Provides
import java.io.IOException
import javax.inject.Named
import javax.inject.Singleton

@Module
class ShowcaseModule {

    private val TAG = ShowcaseModule::class.java.simpleName

    @Provides
    @Singleton
    protected fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        try {
            gsonBuilder.registerTypeAdapter(BaseBlock::class.java, GsonBlockAdapter())
        } catch (e: IOException) {
            Log.w(TAG, "error registering type adapter for GSON deserialization", e)
        } catch (e: ClassNotFoundException) {
            Log.w(TAG, "error registering type adapter for GSON deserialization", e)
        }

        return gsonBuilder.create()
    }

    @Provides
    @Named(value = Constants.BAR_KEY)
    protected fun provideBar(): String {
        return "BAR"
    }

    @Provides
    internal fun provideContext(): Context {
        return ShowcaseApplication.getContext()
    }

    @Provides
    internal fun provideRequestQueue(context: Context): RequestQueue {
        val cache = DiskBasedCache(context.cacheDir, 1024 * 1024) // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        val network = BasicNetwork(HurlStack())
        // Instantiate the RequestQueue with the cache and network.
        val requestQueue = RequestQueue(cache, network)
        // Start the queue
        requestQueue.start()
        return requestQueue
    }

    @Provides
    internal fun provideGetBlocksNetworkService(): GetBlocksNetworkService {
        return GetBlocksNetworkService()
    }

    @Provides
    internal fun provideBlockViewHelper(): BlockViewHelper {
        return BlockViewHelper()
    }


    @Provides
    internal fun provideHomeViewModel(): HomeViewModel {
        return HomeViewModel()
    }
}