package com.mhp.showcase.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mhp.showcase.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ShowcaseModule {

    @Provides
    @Singleton
    protected fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Named(value = Constants.BAR_KEY)
    protected fun provideBar(): String {
        return "BAR"
    }
}