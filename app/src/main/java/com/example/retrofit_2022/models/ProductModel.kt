package com.example.retrofit_2022.models

import com.google.gson.annotations.SerializedName


data class ProductModel(
  @SerializedName("id") val id: Int,
  @SerializedName("nombre") var nombre: String,
  @SerializedName("precio") var precio: Float,
  @SerializedName("cantidad") var cantidad: Int
)