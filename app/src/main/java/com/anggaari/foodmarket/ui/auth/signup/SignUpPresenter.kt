package com.anggaari.foodmarket.ui.auth.signup

import android.net.Uri
import android.view.View
import com.anggaari.foodmarket.model.request.RegisterRequest
import com.anggaari.foodmarket.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class SignUpPresenter(private val view: SignUpContract.View) : SignUpContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitRegister(registerRequest: RegisterRequest, viewParams: View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.register(
            registerRequest.name,
            registerRequest.email,
            registerRequest.password,
            registerRequest.passwordConfirmation,
            registerRequest.address,
            registerRequest.city,
            registerRequest.country,
            registerRequest.phoneNUmber,
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success")) {
                        it.data?.let { it1 -> view.onRegisterSuccess(it1, viewParams) }
                    } else {
                        view.onRegisterFailed(it.meta?.message.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onRegisterFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun submitPhotoRegister(filePath: Uri, viewParams: View) {
        view.showLoading()

        var profileImageFile = File(filePath.path)
        val profileImageRequestBody = profileImageFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val profileImageParams = MultipartBody.Part.createFormData("file", profileImageFile.name, profileImageRequestBody)


        val disposable = HttpClient.getInstance().getApi()!!.registerPhoto(
            profileImageParams,
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success")) {
                        it.data?.let { it1 -> view.onPhotoSuccess(viewParams) }
                    } else {
                        view.onRegisterFailed(it.meta?.message.toString())
                    }
                },
                {
                    view.dismissLoading()
                    view.onRegisterFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }

}