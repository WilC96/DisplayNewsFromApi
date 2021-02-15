package com.example.flowerforallmvvm.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FlowerModel(
    @SerializedName("category")
    @Expose
    var category: String? = null,
    @SerializedName("instructions")
    @Expose
    var instructions: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("photo")
    @Expose
    var photo: String? = null,
    @SerializedName("price")
    @Expose
    var price: Double? = null,
    @SerializedName("productId")
    @Expose
    var productId: Int? = null
) : Parcelable
