package com.anggaari.foodmarket.model.response.checkout


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CheckoutResponse(
    @Expose
    @SerializedName("created_at")
    val createdAt: Int,

    @Expose
    @SerializedName("deleted_at")
    val deletedAt: Any,

    @Expose
    @SerializedName("destination_address")
    val destinationAddress: Any,

    @Expose
    @SerializedName("destination_lat")
    val destinationLat: Any,

    @Expose
    @SerializedName("destination_lng")
    val destinationLng: Any,

    @Expose
    @SerializedName("discount_amount")
    val discountAmount: String,

    @Expose
    @SerializedName("food")
    val food: Food,

    @Expose
    @SerializedName("food_id")
    val foodId: Int,

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("payment_url")
    val paymentUrl: String,

    @Expose
    @SerializedName("quantity")
    val quantity: Int,

    @Expose
    @SerializedName("restaurant_address")
    val restaurantAddress: Any,

    @Expose
    @SerializedName("restaurant_lat")
    val restaurantLat: Any,

    @Expose
    @SerializedName("restaurant_lng")
    val restaurantLng: Any,

    @Expose
    @SerializedName("restaurant_name")
    val restaurantName: String,

    @Expose
    @SerializedName("status")
    val status: String,

    @Expose
    @SerializedName("total")
    val total: String,

    @Expose
    @SerializedName("total_price")
    val totalPrice: String,

    @Expose
    @SerializedName("updated_at")
    val updatedAt: Int,

    @Expose
    @SerializedName("user")
    val user: User,

    @Expose
    @SerializedName("user_id")
    val userId: Int
)