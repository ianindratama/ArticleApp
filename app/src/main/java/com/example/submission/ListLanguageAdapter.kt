package com.example.submission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListLanguageAdapter(private val listLanguage: ArrayList<Language>): RecyclerView.Adapter<ListLanguageAdapter.ListViewHolder>() {

    interface OnItemClickCallback{
        fun onItemClicked(data: Language)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_photo: ImageView = itemView.findViewById(R.id.iv_language_image)
        var tv_language_name: TextView = itemView.findViewById(R.id.tv_language_name)
        var tv_language_detail: TextView = itemView.findViewById(R.id.tv_language_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_language, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val language = listLanguage[position]

        Glide.with(holder.itemView.context)
            .load(language.language_img)
            .apply(RequestOptions().override(75, 75))
            .into(holder.iv_photo)

        val concatenateName: String = language.rank.toString() + ". " + language.name
        holder.tv_language_name.text = concatenateName
        holder.tv_language_detail.text = language.language_desc
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listLanguage[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listLanguage.size
    }
}