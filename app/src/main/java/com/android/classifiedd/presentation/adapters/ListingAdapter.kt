package com.android.classifiedd.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.classifiedd.databinding.ListingItemBinding
import com.android.classifiedd.data.models.ProductListing
import com.android.classifiedd.utils.loadDubizleLogo
import com.android.classifiedd.utils.loadImageFromRemote


class ListingAdapter(private var list: ArrayList<ProductListing>, var itemListener : OnItemClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemHolder(ListingItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ItemHolder
        holder.binding.itemName.text = list[position].name
        if (!list[position].image_urls_thumbnails.isNullOrEmpty()) {
            loadImageFromRemote(holder.binding.thumb, list[position].image_urls_thumbnails[0])
        } else {
            loadDubizleLogo(holder.binding.thumb)
        }

        holder.itemView.setOnClickListener {
            itemListener.onItemPressed(list[position])
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    class ItemHolder(item : ListingItemBinding) : RecyclerView.ViewHolder(item.root){
        var binding = item
    }

    interface OnItemClick {
        fun onItemPressed(product : ProductListing)
    }
}