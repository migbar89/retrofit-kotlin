package com.example.retrofit_2022.models

import com.google.gson.annotations.SerializedName

data class ProductResponse (
  @SerializedName("status") var status:String,
  @SerializedName("datos") var datos: MutableList<ProductModel>)
