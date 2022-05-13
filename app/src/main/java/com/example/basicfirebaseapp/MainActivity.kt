package com.example.basicfirebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicfirebaseapp.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.rvMovieEntries.layoutManager = LinearLayoutManager(this)

        val database = Firebase.database
        val myRef = database.reference

        binding.btnGuardar.setOnClickListener{
            val id = binding.etID.text.toString()
            val title = binding.etTitulo.text.toString()
            val year = binding.etAno.text.toString()
            val type = binding.etTipo.text.toString()
            val poster = binding.etPoster.text.toString()
            val country = binding.etPais.text.toString()
            val genre = binding.etGenero.text.toString()
            myRef.child("pelicula").child("P-"+id).setValue(Movie(id,title,year,type,poster,country,genre))
            myRef.child("pelicula").get().addOnSuccessListener { response ->
                val myjson = JSONObject(response.value.toString())
                var jsonArray = JSONArray()
                var namesArray = myjson.names()

                for(i in 0..namesArray.length()-1){
                    jsonArray.put(myjson.getJSONObject(namesArray[i].toString()))
                }


                Log.d("firebaseResponse", jsonArray.toString())
                binding.rvMovieEntries.adapter = MainAdapter(jsonArray)
            }.addOnFailureListener{
                Log.e("firebaseResponse", "Error getting data", it)
            }
        }

        myRef.child("pelicula").get().addOnSuccessListener { response ->
            val myjson = JSONObject(response.value.toString())
            var jsonArray = JSONArray()
            var namesArray = myjson.names()

            for(i in 0..namesArray.length()-1){
                jsonArray.put(myjson.getJSONObject(namesArray[i].toString()))
            }


            Log.d("firebaseResponse", jsonArray.toString())
            binding.rvMovieEntries.adapter = MainAdapter(jsonArray)
        }.addOnFailureListener{
            Log.e("firebaseResponse", "Error getting data", it)
        }

    }
}