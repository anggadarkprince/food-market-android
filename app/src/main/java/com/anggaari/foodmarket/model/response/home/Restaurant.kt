package com.anggaari.foodmarket.model.response.home

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    @Expose
    @SerializedName("address")
    val address: String?,

    @Expose
    @SerializedName("created_at")
    val createdAt: String?,

    @Expose
    @SerializedName("deleted_at")
    val deletedAt: String?,

    @Expose
    @SerializedName("description")
    val description: String?,

    @Expose
    @SerializedName("id")
    val id: Int?,

    @Expose
    @SerializedName("lat")
    val lat: Float?,

    @Expose
    @SerializedName("lng")
    val lng: Float?,

    @Expose
    @SerializedName("restaurant_name")
    val restaurantName: String?,

    @Expose
    @SerializedName("updated_at")
    val updatedAt: String?
): Parcelable