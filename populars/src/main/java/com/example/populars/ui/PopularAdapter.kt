package com.example.populars.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_data_api.entity.CurrencyApi
import com.example.populars.databinding.FragmentPopularRecyclerItemBinding

class PopularAdapter(private val dataList: List<CurrencyApi>, val callbackAdd: AddItem): RecyclerView.Adapter<PopularAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(private val binding: FragmentPopularRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: CurrencyApi) {
            binding.also {
                it.currency.text = "${currency.name} = "
                it.spot.text = currency.rate.toString()
                it.addFavoritesImageView.setOnClickListener {
                    callbackAdd.add(currency)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = FragmentPopularRecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}