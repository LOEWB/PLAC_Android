package com.example.bny.plac_android

import android.app.Application
import com.example.bny.plac_android.services.AuthenticationService
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Inject

@Component
interface ApplicationComponent {  }

// appComponent lives in the Application class to share its lifecycle
class MyApplication: Application() {
    // Reference to the application graph that is used across the whole app
//    val appComponent = DaggerApplicationComponent.create()
}