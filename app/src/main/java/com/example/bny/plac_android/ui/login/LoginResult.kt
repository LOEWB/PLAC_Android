package com.example.bny.plac_android.ui.login

import com.example.bny.plac_android.ui.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
        val success: LoggedInUserView? = null,
        val error: Int? = null
)
