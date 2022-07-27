package com.example.submission

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var ivLanguageImage: ImageView
    private lateinit var tvLanguageName: TextView
    private lateinit var tvLanguageNumJobs: TextView
    private lateinit var tvLanguageSallary: TextView
    private lateinit var tvLanguageDesc: TextView

    private lateinit var ib_fav: ImageButton
    private lateinit var ib_share: ImageButton

    private lateinit var dbHelper: FeedReaderDbHelper
    private lateinit var rank: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Detail"

        dbHelper = FeedReaderDbHelper(this)

        ivLanguageImage = findViewById(R.id.iv_language_image_detail)
        tvLanguageName = findViewById(R.id.tv_language_name_detail)
        tvLanguageNumJobs = findViewById(R.id.tv_language_numJobs_detail_value)
        tvLanguageSallary = findViewById(R.id.tv_language_avgAnnualSalary_detail_value)
        tvLanguageDesc = findViewById(R.id.tv_language_desc_detail)

        ib_fav = findViewById(R.id.ib_fav)
        ib_share = findViewById(R.id.ib_share)

        val data: Language? = intent.extras?.get("obj") as Language?

        Glide.with(this)
            .load(data?.language_img)
            .into(this.ivLanguageImage)

        rank = data?.rank.toString()
        val name = rank + ". " + data?.name

        tvLanguageName.text = name
        tvLanguageNumJobs.text = data?.numOfJobs
        tvLanguageSallary.text = data?.avg_annual_salary
        tvLanguageDesc.text = data?.language_desc

        ib_fav.setOnClickListener(this)
        ib_share.setOnClickListener(this)

        val db = dbHelper.readableDatabase
        val cursor = checkLike(db)

        if (cursor.count == 0){
            Glide.with(this)
                .load(R.drawable.like_blank)
                .into(this.ib_fav)

        }else{
            Glide.with(this)
                .load(R.drawable.like)
                .into(this.ib_fav)
        }
        cursor.close()

    }

    override fun onClick(p0: View?) {
        when(p0?.id){

            R.id.ib_fav -> {

                var db = dbHelper.readableDatabase

                val cursor = checkLike(db)

                db = dbHelper.writableDatabase

                if (cursor.count == 0){

                    val values = ContentValues().apply {
                        put(FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_RANK, rank)
                    }

                    db?.insert(FeedReaderDbHelper.FeedReaderContract.FeedEntry.TABLE_NAME, null, values)

                    Glide.with(this)
                        .load(R.drawable.like)
                        .into(this.ib_fav)

                    Toast.makeText(this, "Berhasil menambahkan ke My Favourites", Toast.LENGTH_SHORT).show()

                }else{

                    val selection_delete = "${FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_RANK} LIKE ?"
                    val selectionArgs_delete = arrayOf(rank)
                    db.delete(FeedReaderDbHelper.FeedReaderContract.FeedEntry.TABLE_NAME, selection_delete, selectionArgs_delete)

                    Glide.with(this)
                        .load(R.drawable.like_blank)
                        .into(this.ib_fav)

                    Toast.makeText(this, "Berhasil mengeluarkan dari My Favourites", Toast.LENGTH_SHORT).show()

                }
                cursor.close()
            }

            R.id.ib_share -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, tvLanguageDesc.text)
                    type = "text/plain"
                }
                val shareIntent= Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

        }
    }

    private fun checkLike(db: SQLiteDatabase): Cursor{
        // columns from database that we will use
        val projection = arrayOf(BaseColumns._ID, FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_RANK)

        // Filter Result
        val selection = "${FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_RANK} = ?"
        val selectionArgs = arrayOf(rank)

        // sort order
        val sortOrder = "${FeedReaderDbHelper.FeedReaderContract.FeedEntry.COLUMN_NAME_RANK} DESC"

        val cursor = db.query(
            FeedReaderDbHelper.FeedReaderContract.FeedEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
        return cursor
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

}