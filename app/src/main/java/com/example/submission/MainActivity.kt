package com.example.submission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannedString
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var article_image: ImageView
    private lateinit var tv_pypl: TextView

    private lateinit var rv_languages: RecyclerView
    private var list: ArrayList<Language> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Submission"

        tv_pypl = findViewById(R.id.menurut_pypl)
        tv_pypl.text = pyplBold()

        article_image = findViewById(R.id.article_image)
        Glide.with(this)
            .load(R.drawable.banner1)
            .into(article_image)

        rv_languages = findViewById(R.id.rv_languages)
        rv_languages.setHasFixedSize(true)

        list.addAll(LanguageData.listData)
        showList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.action_fav -> {
                val intent = Intent(this@MainActivity, FavouritesActivity::class.java)
                startActivity(intent)
            }

            R.id.action_profile -> {
                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(intent)
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun pyplBold(): SpannedString{
        val string: SpannedString = buildSpannedString {
            append(
                "Pada Artikel kali ini, saya akan membahas 10 Bahasa Pemrograman terpopuler di tahun 2020 menurut "
            )
            bold {
                append("PYPL Popularity of Programming Language")
            }
            append(
                ": "
            )
        }
        return string
    }

    private fun showList(){
        rv_languages.layoutManager = LinearLayoutManager(this)
        val listLanguageAdapter = ListLanguageAdapter(list)
        rv_languages.adapter = listLanguageAdapter

        listLanguageAdapter.setOnItemClickCallback(object: ListLanguageAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Language) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("obj", data as Serializable)
                startActivity(intent)
            }
        })
    }

}