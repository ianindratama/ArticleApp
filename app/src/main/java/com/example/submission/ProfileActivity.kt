package com.example.submission

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProfileActivity : AppCompatActivity() {

    private lateinit var iv_photo: ImageView
    private lateinit var tv_content_article_value1: TextView
    private lateinit var tv_splash_screen_value1: TextView
    private lateinit var tv_splash_screen_value2: TextView
    private lateinit var tv_pojo_intent_value1: TextView
    private lateinit var tv_hyperlink_textview_value1: TextView
    private lateinit var tv_hyperlink_textview_value2: TextView
    private lateinit var tv_recyclerview_value1: TextView
    private lateinit var tv_recyclerview_value2: TextView
    private lateinit var sqlite_in_android_value1: TextView
    private lateinit var nestedScrollView_value1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.title = "Profile"

        iv_photo = findViewById(R.id.iv_photo)
        Glide.with(this)
            .load(R.drawable.my_photo)
            .apply(RequestOptions().override(150, 150))
            .into(iv_photo)

        tv_content_article_value1 = findViewById(R.id.tv_content_article_value1)
        setUpHyperLink(tv_content_article_value1)

        tv_splash_screen_value1 = findViewById(R.id.tv_splash_screen_value1)
        setUpHyperLink(tv_splash_screen_value1)

        tv_splash_screen_value2 = findViewById(R.id.tv_splash_screen_value2)
        setUpHyperLink(tv_splash_screen_value2)

        tv_pojo_intent_value1 = findViewById(R.id.tv_pojo_intent_value1)
        setUpHyperLink(tv_pojo_intent_value1)

        tv_hyperlink_textview_value1 = findViewById(R.id.tv_hyperlink_textview_value1)
        setUpHyperLink(tv_hyperlink_textview_value1)

        tv_hyperlink_textview_value2 = findViewById(R.id.tv_hyperlink_textview_value2)
        setUpHyperLink(tv_hyperlink_textview_value2)

        tv_recyclerview_value1 = findViewById(R.id.tv_recyclerview_value1)
        setUpHyperLink(tv_recyclerview_value1)

        tv_recyclerview_value2 = findViewById(R.id.tv_recyclerview_value2)
        setUpHyperLink(tv_recyclerview_value2)

        sqlite_in_android_value1 = findViewById(R.id.sqlite_in_android_value1)
        setUpHyperLink(sqlite_in_android_value1)

        nestedScrollView_value1 = findViewById(R.id.nestedScrollView_value1)
        setUpHyperLink(nestedScrollView_value1)

    }

    private fun setUpHyperLink(tv: TextView){
        tv.movementMethod = LinkMovementMethod.getInstance()
        tv.setLinkTextColor(Color.BLUE)
        tv.removeLinksUnderline()
    }

    private fun TextView.removeLinksUnderline() {
        val spannable = SpannableString(text)
        for (urlSpan in spannable.getSpans(0, spannable.length, URLSpan::class.java)) {
            spannable.setSpan(object : URLSpan(urlSpan.url) {
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }, spannable.getSpanStart(urlSpan), spannable.getSpanEnd(urlSpan), 0)
        }
        text = spannable
    }

}