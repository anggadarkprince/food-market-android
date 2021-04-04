package com.anggaari.foodmarket.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.anggaari.foodmarket.FoodMarket
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.request.RegisterRequest
import com.anggaari.foodmarket.model.response.login.LoginResponse
import com.anggaari.foodmarket.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sign_up_address.*

class SignUpAddressFragment : Fragment(), SignUpContract.View {

    private lateinit var data: RegisterRequest
    lateinit var presenter: SignUpPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        data = arguments?.getParcelable<RegisterRequest>("data")!!
        presenter = SignUpPresenter(this)

        initListener()
        initView()
    }

    private fun initListener() {
        btnSignUpNow.setOnClickListener { view ->
            val phone = etPhoneNumber.text.toString()
            val address = etAddress.text.toString()
            val city = etCity.text.toString()
            val country = etCountry.text.toString()

            data.let {
                it.address = address
                it.city = city
                it.country = country
                it.phoneNUmber = phone
            }

            when {
                phone.isEmpty() -> {
                    etPhoneNumber.error = "Please input your phone"
                    etPhoneNumber.requestFocus()
                }
                address.isEmpty() -> {
                    etAddress.error = "Please input your address"
                    etAddress.requestFocus()
                }
                city.isEmpty() -> {
                    etCity.error = "Please input your city"
                    etCity.requestFocus()
                }
                country.isEmpty() -> {
                    etCountry.error = "Please input your country"
                    etCountry.requestFocus()
                }
                else -> {
                    presenter.submitRegister(data, view)
                }
            }
        }
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        Log.e("token2", loginResponse.accessToken);
        FoodMarket.getApp().setToken(loginResponse.accessToken)
        val json = Gson().toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        if (data.filePath == null) {
            onPhotoSuccess(view)
        } else {
            presenter.submitPhotoRegister(data.filePath!!, view)
        }
    }

    override fun onPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_signup_success, null)
        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
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

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}