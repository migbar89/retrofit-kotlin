package com.example.retrofit_2022

import AdapterProduct
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_2022.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
  var adapter = AdapterProduct()
  private lateinit var binding:   ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)


    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)


    binding.recycleProducto.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)

    binding.recycleProducto.adapter = adapter
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.menu_add_producto) {

      val builder = AlertDialog.Builder(this)
      val inflater = layoutInflater
      //builder.setTitle("Nuevo Producot")
      val dialogLayout = inflater.inflate(R.layout.activity_nuevoprod, null)

      val etnombre = dialogLayout.findViewById<EditText>(R.id.et_nuevo_nombre)
      val etprecio = dialogLayout.findViewById<EditText>(R.id.et_nuevo_precio)
      val etcantidad = dialogLayout.findViewById<EditText>(R.id.et_nuevo_cantidad)


      builder.setView(dialogLayout)
      builder.setPositiveButton("Guardar") { dialogInterface, i ->
        var pro = ProductoModel(
          0,
          etnombre.text.toString(),
          etprecio.text.toString().toFloat(),
          etcantidad.text.toString().toInt()
        )

        CoroutineScope(Dispatchers.IO).launch {
          // service.guardarProductos(applicationContext, pro)

        }
        //  service.obtenerProductos(adapter)


      }
      builder.show()

    }

    return true
  }
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_producto, menu)
    return super.onCreateOptionsMenu(menu)
  }
}