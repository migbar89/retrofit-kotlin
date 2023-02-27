package com.example.retrofit_2022.retrofit

import com.example.retrofit_2022.models.ProductResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiProduct {
  @GET("obtenerProductos")
  suspend fun obtenerTodosProductos(): Response<ProductResponse>

  @FormUrlEncoded
  @POST("guadarProducto")
  suspend fun guardarProducto(@Field("nombre") nombre: String,
                              @Field("precio") precio : Int,
                              @Field("cantidad") cantidad : Int) : Response<ProductResponse>

  @FormUrlEncoded
  @POST("actualizarProducto")
  suspend fun actualizarProducto(@Field("id") id: Int, @Field("nombre") nombre: String,
                                 @Field("precio") precio : Int,
                                 @Field("cantidad") cantidad : Int) : Response<ProductResponse>

  @FormUrlEncoded
  @POST("eliminarProducto")
  suspend fun eliminarProducto(@Field("id") id: Int ) : Response<ProductResponse>

}