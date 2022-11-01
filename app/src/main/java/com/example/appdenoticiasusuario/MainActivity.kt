package com.example.appdenoticiasusuario

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appdenoticiasusuario.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        recuperarnoticia()
    }
    private fun recuperarnoticia(){
        db.collection("noticias").document("noticias").get()
            .addOnCompleteListener{
                documento ->
                if (documento.isSuccessful){
                    val titulo = documento.result.get("titulo").toString()
                    val noticia = documento.result.get("texto").toString()
                    val autor = documento.result.get("autor").toString()
                    val data = documento.result.get("data").toString()

                    binding.TxtTituloNoticia.text = titulo
                    binding.TxtNoticia.text = noticia
                    binding.autorNoticia.text = autor
                    binding.dataNoticia.text = data
                }
            }

    }
}
