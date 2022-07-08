package com.example.retrofit_2022

import com.google.gson.annotations.SerializedName


data class ProductoModel(
  @SerializedName("id") val id: Int,
  @SerializedName("nombre") var nombre: String,
  @SerializedName("precio") var precio: Float,
  @SerializedName("cantidad") var cantidad: Int
)