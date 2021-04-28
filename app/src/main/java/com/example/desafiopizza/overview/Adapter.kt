package com.example.desafiopizza

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiopizza.fragments.ListaPizzasFragmentsDirections
import com.example.desafiopizza.network.Pizza


class MyAdapter(private val data: List<Pizza>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(), Filterable  {
    private val filteredData = data.toMutableList()
    private val filter = MyFilter(data) {
        filteredData.clear()
        filteredData.addAll(it)
        notifyDataSetChanged()
    }

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){




        fun bind(property: Pizza){
            val nome = view.findViewById<TextView>(R.id.nome)
            val foto = view.findViewById<ImageView>(R.id.foto)
            val preco = view.findViewById<TextView>(R.id.precop)
            val estrelas = view.findViewById<RatingBar>(R.id.ratingBar)
            val precoFormatado = "%.2f".format(property.priceP)

            nome.text = "Pizza de ${property.name}"
            preco.text = "R$ ${precoFormatado}".replace(".",",")
            estrelas.rating = property.rating.toFloat()
            view.setOnClickListener {
                Navigation.findNavController(view).navigate(
                    ListaPizzasFragmentsDirections.actionListaPizzasFragmentsToDetalhesPizzaFragment(property)
                )
            }

            Glide.with(view.context).load(property.imageUrl).centerCrop().into(foto)
        }
    }

    class MyFilter(private val data: List<Pizza>, private val publishresult: (List<Pizza>) -> Unit): android.widget.Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val result = FilterResults()
            val filtered = if (constraint == null || constraint.isEmpty() || constraint.isBlank())
                data
            else data.filter { it.name.contains(constraint?.toString() ?: "", ignoreCase = true) }
            result.values = filtered
            result.count = filtered.size
            return result
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results == null) return
            publishresult.invoke(results.values as List<Pizza>)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return filteredData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(filteredData[position])

    }

    override fun getFilter(): android.widget.Filter {
        return  filter
    }


}