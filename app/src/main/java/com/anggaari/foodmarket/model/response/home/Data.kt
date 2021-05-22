package com.anggaari.foodmarket.model.response.home

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Expose
    @SerializedName("category")
    val category: String?,

    @Expose
    @SerializedName("created_at")
    val createdAt: Int?,

    @Expose
    @SerializedName("deleted_at")
    val deletedAt: String?,

    @Expose
    @SerializedName("description")
    val description: String?,

    @Expose
    @SerializedName("food_name")
    val foodName: String?,

    @Expose
    @SerializedName("id")
    val id: Int?,

    @Expose
    @SerializedName("image")
    val image: String?,

    @Expose
    @SerializedName("image_url")
    val imageUrl: String?,

    @Expose
    @SerializedName("ingredients")
    val ingredients: String?,

    @Expose
    @SerializedName("price")
    val price: String?,

    @Expose
    @SerializedName("rating")
    val rating: Float?,

    @Expose
    @SerializedName("restaurant")
    val restaurant: Restaurant?,

    @Expose
    @SerializedName("restaurant_id")
    val restaurantId: Int?,

    @Expose
    @SerializedName("types")
    val types: String?,

    @Expose
    @SerializedName("updated_at")
    val updatedAt: Int?
): Parcelable