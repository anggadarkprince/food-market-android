package com.anggaari.foodmarket.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.anggaari.foodmarket.FoodMarket
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.response.login.LoginResponse
import com.anggaari.foodmarket.ui.MainActivity
import com.anggaari.foodmarket.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment(), SignInContract.View {

    lateinit var presenter: SignInPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SignInPresenter(this)

        if (!FoodMarket.getApp().getToken().isNullOrEmpty()) {
            launchHomeActivity()
        }

        initView()

        etEmail.setText("anggadarkprince@gmail.com")
        etPassword.setText("anggaari")

        btnSignIn.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty()) {
                etEmail.error = "Please input your email"
                etEmail.requestFocus()
            } else if (password.isEmpty()) {
                etPassword.error = "Please input your password"
                etPassword.requestFocus()
            } else {
                presenter.submitLogin(email, password)
            }
        }

        btnSignUp.setOnClickListener {
            val signUp = Intent(activity, AuthActivity::class.java)
            signUp.putExtra("page_request", 2)
            startActivity(signUp)
        }
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun launchHomeActivity() {
        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        FoodMarket.getApp().setToken(loginResponse.access_token)
        val json = Gson().toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        launchHomeActivity()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}