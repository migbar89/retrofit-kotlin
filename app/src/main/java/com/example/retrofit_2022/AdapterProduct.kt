import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_2022.R
import com.example.retrofit_2022.publicData
import com.example.retrofit_2022.retrofit.ServiceRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AdapterProduct() : RecyclerView.Adapter<AdapterProduct.Viewholder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProduct.Viewholder {

    val v = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
    //Toast.makeText(p0.context,firestore.listacliente.size.toString(),Toast.LENGTH_SHORT).show()
    return Viewholder(v)
  }


  override fun getItemCount(): Int {
    return publicData.listaproducto.size
  }

  override fun onBindViewHolder(holder: AdapterProduct.Viewholder, position: Int) {
    val pro = publicData.listaproducto.get(position)
    holder.tvnombre.setText(pro.nombre.toString());
    holder.tvprecio.setText(pro.precio.toString());
    holder.tvcantidad.setText(pro.cantidad.toString());
    holder.img_eliminar.setOnClickListener {
      Log.e("Eliminando", "Eliminando")
      GlobalScope.launch {
           ServiceRetrofit.eliminarProducto(holder.itemView.context,pro.id)

      }
        ServiceRetrofit.obtenerProductos(this@AdapterProduct)
    }

    holder.img_editar.setOnClickListener {
      val builder = androidx.appcompat.app.AlertDialog.Builder(holder.itemView.context)
      val dialogLayout = LayoutInflater.from(holder.itemView.context).inflate(R.layout.activity_nuevoprod, null)

      val etnombre = dialogLayout.findViewById<EditText>(R.id.et_nuevo_nombre)
      val etprecio = dialogLayout.findViewById<EditText>(R.id.et_nuevo_precio)
      val etcantidad = dialogLayout.findViewById<EditText>(R.id.et_nuevo_cantidad)

      etnombre.setText(pro.nombre)
      etprecio.setText(pro.precio.toString())
      etcantidad.setText(pro.cantidad.toString())

      builder.setView(dialogLayout)
      builder.setPositiveButton("Guardar") { dialogInterface, i ->


        pro.nombre = etnombre.text.toString()
        pro.precio = etprecio.text.toString().toFloat()
        pro.cantidad = etcantidad.text.toString().toInt()
        CoroutineScope(Dispatchers.IO).launch {
          ServiceRetrofit.actualizarProductos(holder.itemView.context, pro)

        }
        ServiceRetrofit.obtenerProductos(this@AdapterProduct)
      }
      builder.show()
    }

  }

  private fun mostrarModal(holder: Viewholder) {

  }


  class Viewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val tvnombre: TextView = itemview.findViewById(R.id.tv_nombre)
    val tvprecio: TextView = itemview.findViewById(R.id.tv_precio)
    val tvcantidad: TextView = itemview.findViewById(R.id.tv_cantidad)
    val img_eliminar: ImageView = itemview.findViewById(R.id.img_eliminar)
    val img_editar: ImageView = itemview.findViewById(R.id.img_editar)
  }

}