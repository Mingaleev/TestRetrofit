package com.example.favoruites.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_data_api.entity.CurrencyApi
import com.example.favoruites.databinding.FragmentFavouritesRecyclerItemBinding

class FavouritesAdapter(private val dataList: List<CurrencyApi>, val callbackRemove: RemoveItem) :
    RecyclerView.Adapter<FavouritesAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(currency: CurrencyApi) {
            FragmentFavouritesRecyclerItemBinding.bind(itemView).also {
                it.currency.text = "${currency.name} = "
                it.spot.text = currency.rate.toString()
                it.removeFavoritesImageView.setOnClickListener {
                    callbackRemove.delete(currency.name)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = FragmentFavouritesRecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        return CurrencyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}