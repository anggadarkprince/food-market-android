package com.anggaari.foodmarket.model.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @Expose
    @SerializedName("address")
    val address: String,

    @Expose
    @SerializedName("city")
    val city: String,

    @Expose
    @SerializedName("created_at")
    val createdAt: String,

    @Expose
    @SerializedName("current_team_id")
    val currentTeamId: Any,

    @Expose
    @SerializedName("email")
    val email: String,

    @Expose
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,

    @Expose
    @SerializedName("country")
    val country: String,

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("phone_number")
    val phoneNumber: String,

    @Expose
    @SerializedName("profile_photo_path")
    val profilePhotoPath: String,

    @Expose
    @SerializedName("profile_photo_url")
    val profilePhotoUrl: String,

    @Expose
    @SerializedName("role")
    val role: String,

    @Expose
    @SerializedName("updated_at")
    val updatedAt: String
)