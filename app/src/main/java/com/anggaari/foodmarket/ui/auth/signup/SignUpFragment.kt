package com.anggaari.foodmarket.ui.auth.signup

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.anggaari.foodmarket.R
import com.anggaari.foodmarket.model.request.RegisterRequest
import com.anggaari.foodmarket.ui.auth.AuthActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    var filePath: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummy()
        initListener()
    }

    private fun initListener() {
        ivProfile.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }

        btnContinue.setOnClickListener {
            val fullName = etFullName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (fullName.isEmpty()) {
                etFullName.error = "Please input your full name"
                etFullName.requestFocus()
            } else if (email.isEmpty()) {
                etEmail.error = "Please input your email"
                etEmail.requestFocus()
            } else if (password.isEmpty()) {
                etPassword.error = "Please input your password"
                etPassword.requestFocus()
            } else {
                val data = RegisterRequest(
                    fullName,
                    email,
                    password,
                    password,
                    "",
                    "",
                    "",
                    "",
                    filePath
                )

                val bundle = Bundle()
                bundle.putParcelable("data", data)

                Navigation.findNavController(it)
                    .navigate(R.id.action_signup_address, bundle)
                (activity as AuthActivity).toolbarSignUpAddress()
            }

        }
    }

    private fun initDummy() {
        etFullName.setText("Arie Akie")
        etEmail.setText("arie@mail.com")
        etPassword.setText("anggaari")
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            filePath = data?.data!!

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

}