package ru.mingaleev.testretrofit.ui.favoruites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mingaleev.testretrofit.databinding.FragmentFavouritesRecyclerItemBinding
import ru.mingaleev.testretrofit.databinding.FragmentPopularRecyclerItemBinding
import ru.mingaleev.testretrofit.domain.entity.Currency

class FavouritesAdapter(private val dataList: List<Currency>, val callbackRemove: RemoveItem) :
    RecyclerView.Adapter<FavouritesAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(currency: Currency) {
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