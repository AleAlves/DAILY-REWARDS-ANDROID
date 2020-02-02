package br.com.aleson.daily.rewards.app.core.firebase

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import java.util.*


const val RC_SIGN_IN = 9001
const val FIREBASE_ID_CONFIG = "firebase_id"
const val CONFIG_FILE = "onfig.properties"

class FirebaseAuthHelper(private val context: Context?) {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var googleSignInClient: GoogleSignInClient? = null

    fun user(): FirebaseUser? {
        return mAuth.currentUser
    }

    fun auth(): FirebaseAuth? {
        return mAuth
    }

    fun signInClient(): GoogleSignInClient? {
        return googleSignInClient
    }


    fun iniGoogleSignInClient(context: Context?) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getID())
            .requestEmail()
            .build()

        this.googleSignInClient = context?.let { GoogleSignIn.getClient(it, gso) }
    }

    private fun getID(): String? {
        val properties = Properties()
        val inputStream = context?.assets?.open(CONFIG_FILE)
        properties.load(inputStream)
        val id = properties.getProperty(FIREBASE_ID_CONFIG)
        return id
    }

    fun authWithGoogle(
        activity: Activity,
        data: Intent?,
        onSuccess: (FirebaseUser) -> Unit,
        onFail: () -> Unit
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account = task.getResult(ApiException::class.java)
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        mAuth.signInWithCredential(credential)
            ?.addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    this.user()?.let { onSuccess(it) }
                } else {
                    onFail()
                }
            }
    }


}