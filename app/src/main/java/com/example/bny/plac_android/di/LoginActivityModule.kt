package com.example.bny.plac_android.di

import android.content.Context
import com.example.bny.plac_android.services.AuthenticationService
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule constructor(private val context: Context){

    @Provides
    fun context(): Context {
        return context
    }
}

