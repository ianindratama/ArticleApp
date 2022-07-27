package com.example.submission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListFavsLanguageAdapter(private val listFavsLanguage: ArrayList<Language>): RecyclerView.Adapter<ListFavsLanguageAdapter.ListViewHolder>(){

    interface OnItemClickCallback{
        fun onItemClicked(favsData: Language)
        fun onItemDeleteClicked(favsData: Language)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_favslanguage_image: ImageView = itemView.findViewById(R.id.iv_favslanguage_image)
        var tv_favslanguage_name: TextView = itemView.findViewById(R.id.tv_favslanguage_name)
        var tv_favslanguage_detail: TextView = itemView.findViewById(R.id.tv_favslanguage_detail)
        var ib_delete: ImageButton = itemView.findViewById(R.id.ib_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_favslanguage, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val language = listFavsLanguage[position]

        Glide.with(holder.itemView.context)
            .load(language.language_img)
            .apply(RequestOptions().override(75, 75))
            .into(holder.iv_favslanguage_image)

        holder.tv_favslanguage_name.text = language.name
        holder.tv_favslanguage_detail.text = language.language_desc
        holder.ib_delete.setOnClickListener {
            holder.itemView.isEnabled = false
            it.isEnabled = false
            onItemClickCallback.onItemDeleteClicked(listFavsLanguage[holder.adapterPosition])
            notifyItemRemoved(position)
            listFavsLanguage.removeAt(position)
            notifyItemRangeChanged(position, listFavsLanguage.size)
        }
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listFavsLanguage[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listFavsLanguage.size
    }

}

