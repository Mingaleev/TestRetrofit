package ru.mingaleev.testretrofit.ui.favoruites

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mingaleev.testretrofit.domain.entity.Currency
import ru.mingaleev.testretrofit.databinding.FragmentPopularRecyclerItemBinding

class FavouritesAdapter(private val dataList: List<Currency>): RecyclerView.Adapter<FavouritesAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(currency: Currency) {
            FragmentPopularRecyclerItemBinding.bind(itemView).also {
                it.currency.text = "${currency.name} = "
                it.spot.text = currency.rate.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = FragmentPopularRecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        return CurrencyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}