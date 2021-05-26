package com.anggaari.foodmarket.network

import com.anggaari.foodmarket.model.response.Wrapper
import com.anggaari.foodmarket.model.response.checkout.CheckoutResponse
import com.anggaari.foodmarket.model.response.home.HomeResponse
import com.anggaari.foodmarket.model.response.login.LoginResponse
import com.anggaari.foodmarket.model.response.transaction.TransactionResponse
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

    @GET("foods")
    fun home(): Observable<Wrapper<HomeResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(@Field("food_id") food_id:String,
                 @Field("user_id") user_id:String,
                 @Field("quantity") quantity:String,
                 @Field("total") total:String,
                 @Field("status") status:String): Observable<Wrapper<CheckoutResponse>>

    // transaction,
    // Progress = on delivery
    // Past order = delivered
    @GET("transactions")
    fun transaction(): Observable<Wrapper<TransactionResponse>>

    // transaction update,
    @FormUrlEncoded
    @POST("transaction/{id}")
    fun transactionUpdate(@Path(value = "id") userId:String,
                          @Field("status") status: String): Observable<Wrapper<Any>>
}