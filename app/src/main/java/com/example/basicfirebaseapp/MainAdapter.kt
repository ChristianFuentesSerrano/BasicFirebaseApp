package com.example.basicfirebaseapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicfirebaseapp.databinding.ItemPeliculaBinding
import com.google.firebase.database.DataSnapshot
import org.json.JSONArray
import org.json.JSONObject

class MainAdapter(private val movies: JSONArray): RecyclerView.Adapter<MainAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainHolder {
        val binding = ItemPeliculaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainHolder, position: Int) {
        if(movies[position] != null) {
            holder.render(movies.getJSONObject(position))
        }
    }

    override fun getItemCount(): Int = movies.length()


    class MainHolder(val binding: ItemPeliculaBinding):RecyclerView.ViewHolder(binding.root) {
        fun render(pelicula: JSONObject){
            binding.tvTitulo.setText(pelicula.getString("title"))
            binding.tvAno.setText(pelicula.getString("year"))
            binding.tvID.setText(pelicula.getString("imdbID"))
            binding.tvTipo.setText(pelicula.getString("type"))
            binding.tvPoster.setText(pelicula.getString("poster"))
            binding.tvPais.setText(pelicula.getString("country"))
            binding.tvGenero.setText(pelicula.getString("genre"))
        }
    }
}