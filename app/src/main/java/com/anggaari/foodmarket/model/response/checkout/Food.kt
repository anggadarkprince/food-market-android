package com.anggaari.foodmarket.model.response.checkout


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Food(
    @Expose
    @SerializedName("category")
    val category: String,

    @Expose
    @SerializedName("created_at")
    val createdAt: Int,

    @Expose
    @SerializedName("deleted_at")
    val deletedAt: Any,

    @Expose
    @SerializedName("description")
    val description: String,

    @Expose
    @SerializedName("food_name")
    val foodName: String,

    @Expose
    @SerializedName("formatted_price")
    val formattedPrice: String,

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("image")
    val image: String,

    @Expose
    @SerializedName("image_url")
    val imageUrl: String,

    @Expose
    @SerializedName("ingredients")
    val ingredients: String,

    @Expose
    @SerializedName("price")
    val price: String,

    @Expose
    @SerializedName("rating")
    val rating: Int,

    @Expose
    @SerializedName("restaurant_id")
    val restaurantId: Int,

    @Expose
    @SerializedName("types")
    val types: String,

    @Expose
    @SerializedName("updated_at")
    val updatedAt: Int
)