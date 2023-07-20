package com.coroutines_retrofit_mvvm

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler
import com.coroutines_retrofit_mvvm.utils.Apphelper
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Locale

@RunWith(AndroidJUnit4::class)
class LoginTest {

    var username = "17A-Parthiban"

    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        Apphelper.init(context)
    }

    @Test
    fun testLoginWithValidCredentials() {
        Apphelper.getPool().getUser(username).getSessionInBackground(authenticationHandler)
        Assert.assertTrue(true)
    }

    @Test
    fun testLoginWithWrongCredentials() {
        Apphelper.getPool().getUser("username").getSessionInBackground(authenticationHandler)
        Assert.assertFalse(false)
    }

    private val authenticationHandler: AuthenticationHandler = object : AuthenticationHandler {
        override fun onSuccess(cognitoUserSession: CognitoUserSession, device: CognitoDevice?) {
            val idToken: String = cognitoUserSession.idToken.jwtToken
            Log.e("Auth Success", cognitoUserSession.getUsername() + idToken)
            Apphelper.getPool().getUser(username).getDetailsInBackground(detailsHandler)
        }

        override fun getAuthenticationDetails(
            authenticationContinuation: AuthenticationContinuation?,
            userId: String?
        ) {
            Locale.setDefault(Locale.US)
            Log.e("authenticationDetails ", userId!!)
        }

        override fun getMFACode(multiFactorContinuation: MultiFactorAuthenticationContinuation?) {
            Log.e("TAG", "getMFACode")
        }

        override fun onFailure(e: Exception) {
            Log.e("TAG failure", e.message!!)
        }

        override fun authenticationChallenge(continuation: ChallengeContinuation?) {
            Log.e("authenticationChanllenge", java.lang.String.valueOf(continuation))
        }
    }

    // getting user details from server
    val detailsHandler: GetDetailsHandler = object : GetDetailsHandler {
        override fun onSuccess(cognitoUserDetails: CognitoUserDetails) {
            Log.e(
                "cognitoUserDetails :",
                cognitoUserDetails.attributes.attributes.toString()
            )
            val userDetailsMap: Map<String, String> =
                cognitoUserDetails.attributes.attributes
            val masterId = userDetailsMap["name"]
            Log.e("master_id :", masterId!!)
        }

        override fun onFailure(exception: Exception) {
            Log.e("cognitoUser failure", exception.message!!)
        }
    }
}
