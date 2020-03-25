package com.example.bny.plac_android.di

import com.example.bny.plac_android.MainActivity
import com.example.bny.plac_android.services.AuthenticationService
import com.example.bny.plac_android.ui.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[MainActivityModule::class])
interface ApplicationComponent {
    fun getAuthService(): AuthenticationService
    fun inject(mainActivity: MainActivity)
    fun injectLogin(loginActivity: LoginActivity)
}