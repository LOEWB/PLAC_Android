package com.example.bny.plac_android.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule constructor(val context: Context){

    @Provides
    fun context(): Context {
        return context
    }

}