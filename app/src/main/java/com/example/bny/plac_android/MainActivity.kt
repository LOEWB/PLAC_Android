package com.example.bny.plac_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bny.plac_android.services.AuthenticationService
import javax.inject.Inject

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var authenticationService = AuthenticationService(this.applicationContext)

        Log.d("PLAC_APP", "msg: " + authenticationService.getToken())
    }
}
