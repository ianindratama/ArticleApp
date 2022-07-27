package com.example.submission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator
import java.io.Serializable

class FavouritesActivity : AppCompatActivity() {

    private lateinit var fl_myFavs: FrameLayout
    private lateinit var iv_bgFav: ImageView
    private lateinit var rv_favsLang: RecyclerView
    private lateinit var tv_no_favs_data: TextView

    private var favsList: ArrayList<Language> = arrayListOf()

    private lateinit var dbHelper: FeedReaderDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        supportActionBar?.title = "Favourites"

        fl_myFavs = findViewById(R.id.fl_myFavs)
        fl_myFavs.requestFocusFromTouch()

        iv_bgFav = findViewById(R.id.iv_bgFav)

        Glide.with(this)
            .load(R.drawable.fav_bg2)
            .into(iv_bgFav)

        rv_favsLang = findViewById(R.id.rv_favsLang)
        rv_favsLang.setHasFixedSize(true)
        rv_favsLang.itemAnimator = SlideInRightAnimator()
        rv_favsLang.itemAnimator?.apply {
            removeDuration = 1000
            changeDuration = 3000
        }

        tv_no_favs_data = findViewById(R.id.tv_no_favs_data)

        favsList.addAll(fetchFavsLang())

        if (favsList.size <= 0){
            noDataView()
        }else{
            tv_no_favs_data.visibility = View.GONE
            rv_favsLang.visibility = View.VISIBLE
            showList()
        }

    }

    fun noDataView(){
        tv_no_favs_data.visibility = View.VISIBLE
        rv_favsLang.visibility = View.GONE
    }

    private fun fetchFavsLang(): ArrayList<Language> {

        dbHelper = FeedReaderDbHelper(this)
        val db = dbHelper.readableDatabase

        val projection = arrayOf(FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_RANK)

        val cursor = db.query(
            FeedReaderDbHelper.FeedReaderContract.FeedEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        val favsLangIndexes = mutableListOf<Int>()
        with(cursor){
            while (moveToNext()){
                val favsLangIndex = getString(getColumnIndexOrThrow(FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_RANK))
                favsLangIndexes.add(favsLangIndex.toInt())
            }
        }

        cursor.close()

        val favsListData: ArrayList<Language> = arrayListOf()

        for (position in favsLangIndexes){
            favsListData.add(LanguageData.listData[position - 1])
        }

        return favsListData

    }

    private fun showList() {
        rv_favsLang.layoutManager = LinearLayoutManager(this)
        val listFavsLanguageAdapter = ListFavsLanguageAdapter(favsList)
        listFavsLanguageAdapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                super.onChanged()
                checkEmpty()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                checkEmpty()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)
                checkEmpty()
            }

            fun checkEmpty(){
                if (favsList.size == 0) noDataView()
            }
        })

        rv_favsLang.adapter = listFavsLanguageAdapter

        val db = dbHelper.writableDatabase

        listFavsLanguageAdapter.setOnItemClickCallback(object: ListFavsLanguageAdapter.OnItemClickCallback{
            override fun onItemClicked(favsData: Language) {
                val intent = Intent(this@FavouritesActivity, DetailActivity::class.java)
                intent.putExtra("obj", favsData as Serializable)
                startActivity(intent)
            }

            override fun onItemDeleteClicked(favsData: Language) {
                val selectionFavs_delete = "${FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_RANK} LIKE ?"
                val selectionArgsFavs_delete = arrayOf(favsData.rank.toString())
                db.delete(FeedReaderDbHelper.FeedReaderContract.FeedEntry.TABLE_NAME, selectionFavs_delete, selectionArgsFavs_delete)
            }
        })
    }

    override fun onRestart() {
        recreate()
        super.onRestart()
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }


}