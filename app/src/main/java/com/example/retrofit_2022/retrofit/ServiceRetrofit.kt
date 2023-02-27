package com.example.retrofit_2022.retrofit

import AdapterProduct
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.retrofit_2022.models.ProductModel
import com.example.retrofit_2022.publicData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceRetrofit {
  private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl("http://192.168.0.101/web-api/public/api/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  suspend fun guardarProductos(context: Context, product: ProductModel) {
    CoroutineScope(Dispatchers.IO).launch {

      withContext(Dispatchers.Main) {
        try {
          val response = getRetrofit().create(ApiProduct::class.java)
            .guardarProducto(product.nombre, product.precio.toInt(), product.cantidad)
          Log.e("Datos", response.body().toString())
          if (response.isSuccessful && response.body() != null) {
            Toast.makeText(context, "Producto Guardado", Toast.LENGTH_SHORT).show()
          } else {
            Log.e("Datos VAcios", "DAtos vacios")
          }

        } catch (e: Exception) {
          Log.e("Error", e.message.toString())
        }


      }
    }
  }

  suspend fun actualizarProductos(context: Context, producto: ProductModel) {
    CoroutineScope(Dispatchers.IO).launch {


      withContext(Dispatchers.Main) {
        try {
          val response = getRetrofit().create(ApiProduct::class.java)
            .actualizarProducto(producto.id, producto.nombre, producto.precio.toInt(), producto.cantidad)
          Log.e("Datos", response.body().toString())
          if (response.isSuccessful && response.body() != null) {
            Toast.makeText(context, "Producto Guardado", Toast.LENGTH_SHORT).show()


          } else {
            Log.e("Datos VAcios", "DAtos vacios")
          }

        } catch (e: Exception) {
          Log.e("Error", e.message.toString())
        }


      }
    }
  }

  suspend fun eliminarProducto(context: Context, id: Int) {
    CoroutineScope(Dispatchers.IO).launch {
      withContext(Dispatchers.Main) {
        try {
          val response = getRetrofit().create(ApiProduct::class.java)
            .eliminarProducto(id)

          if (response.isSuccessful && response.body()!!.status == "success") {
            Toast.makeText(context, "Producto Eliminado", Toast.LENGTH_SHORT).show()
          } else {
            Log.e("Datos Vacios", "Datos Vacios")
          }
        } catch (e: Exception) {
          Log.e("Error", e.message.toString())
        }


      }
    }
  }

  fun obtenerProductos(adapter: AdapterProduct) {
    Log.e("Consultando datos ", "Consultando")
    CoroutineScope(Dispatchers.IO).launch {
      withContext(Dispatchers.Main) {
        try {
          val response =
            getRetrofit().create(ApiProduct::class.java).obtenerTodosProductos()
          Log.e("Datos", response.body().toString())
          if (response.isSuccessful && response.body() != null) {
            Log.e("Datos", response.body()!!.datos.count().toString())
            publicData.listaproducto = response.body()!!.datos
            adapter.notifyDataSetChanged()
            Log.e("Actualizando", "Actualizando Datos")
          } else {
            Log.e("Datos VAcios", "DAtos vacios")
          }
        } catch (e: Exception) {
          Log.e("Error", e.message.toString())
        }
      }
    }


  }
}