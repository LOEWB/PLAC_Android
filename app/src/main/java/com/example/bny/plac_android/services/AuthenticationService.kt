package com.example.bny.plac_android.services

import android.content.Context
import com.example.bny.plac_android.repositories.AuthenticationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(val rep: AuthenticationRepository)/* @Inject constructor(private val repo: AuthenticationRepository)*/ {

//    val rep: AuthenticationRepository = AuthenticationRepository()

    fun getToken(): String {
        return rep.retrieveToken()
    }
}