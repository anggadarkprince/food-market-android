package com.anggaari.foodmarket.network

import com.anggaari.foodmarket.model.response.Wrapper
import com.anggaari.foodmarket.model.response.login.LoginResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface Endpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email") email:String, @Field("password") password:String): Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST("register")
    fun register(@Field("name") name:String,
                 @Field("email") email:String,
                 @Field("password") password:String,
                 @Field("password_confirmation") passwordConfirmation:String,
                 @Field("address") address:String,
                 @Field("city") city:String,
                 @Field("country") country:String,
                 @Field("phone_number") phoneNumber:String): Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(@Part profileImage:MultipartBody.Part): Observable<Wrapper<Any>>
}