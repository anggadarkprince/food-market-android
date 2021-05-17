package com.anggaari.foodmarket.model.response.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("address")
    val address: Any,

    @Expose
    @SerializedName("city")
    val city: Any,

    @Expose
    @SerializedName("country")
    val country: Any,

    @Expose
    @SerializedName("created_at")
    val createdAt: Int,

    @Expose
    @SerializedName("email")
    val email: String,

    @Expose
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("phone_number")
    val phoneNumber: Any,

    @Expose
    @SerializedName("profile_photo_path")
    val profilePhotoPath: Any,

    @Expose
    @SerializedName("profile_photo_url")
    val profilePhotoUrl: String,

    @Expose
    @SerializedName("role")
    val role: String,

    @Expose
    @SerializedName("updated_at")
    val updated_at: Int
)