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
    val created_at: Int,
    @Expose
    @SerializedName("current_team_id")
    val current_team_id: Int,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("email_verified_at")
    val email_verified_at: Any,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("phone_number")
    val phone_number: Any,
    @Expose
    @SerializedName("profile_photo_path")
    val profile_photo_path: Any,
    @Expose
    @SerializedName("profile_photo_url")
    val profile_photo_url: String,
    @Expose
    @SerializedName("role")
    val role: String,
    @Expose
    @SerializedName("updated_at")
    val updated_at: Int
)