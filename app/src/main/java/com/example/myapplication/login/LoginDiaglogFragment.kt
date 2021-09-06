package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.example.myapplication.R
import com.example.myapplication.register.RegisterActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit.RetrofitClient
import retrofit2.Retrofit

class LoginDiaglogFragment : DialogFragment() {

//    lateinit var loginBtn : SignInButton
    lateinit var registerBtn : Button

    lateinit var ipSetBtn : Button

    lateinit var ipTV : TextView

    lateinit var ipSetET : EditText

    lateinit var firebaseAuth : FirebaseAuth
    lateinit var googleClient : GoogleSignInClient


    override fun onResume() {
        super.onResume()

        // Device Width 기준 0.9배로 설정
        val windowManager = activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v : View = inflater.inflate(R.layout.dialog_fragment_login, container)
//        loginBtn = v.findViewById(R.id.google_signin_btn)
        ipTV = v.findViewById(R.id.ip_tv)
        ipTV.setText(RetrofitClient.getURL())

        ipSetET = v.findViewById(R.id.ip_et)

        registerBtn = v.findViewById(R.id.register_btn)
        registerBtn.setOnClickListener {
            openRegister()
        }

        ipSetBtn = v.findViewById(R.id.ip_set_btn)
        ipSetBtn.setOnClickListener{
            val newIP : String = ipSetET.text.toString()
            Log.d("NEW IP", newIP)
            RetrofitClient.setURL(newIP)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("coderkangkim@gmail.com")
            //'R.string.default_web_client_id' 에는 본인의 클라이언트 아이디를 넣어주시면 됩니다.
            //저는 스트링을 따로 빼서 저렇게 사용했지만 스트링을 통째로 넣으셔도 됩니다.
            .requestEmail()
            .build()

        googleClient = GoogleSignIn.getClient(this.activity, gso)

        firebaseAuth = Firebase.auth

        return v

    }
    private fun openRegister() {
//        val signInIntent = googleClient.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
        val registerIntent = Intent(context, RegisterActivity::class.java)

        startActivity(registerIntent)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        // 로그인 됐을 경우
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    } //onStart End
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = firebaseAuth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}