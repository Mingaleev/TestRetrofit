package ru.mingaleev.testretrofit.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mingaleev.testretrofit.data.dto.Rate
import ru.mingaleev.testretrofit.databinding.FragmentPopularRecyclerItemBinding

class PopularAdapter(private val dataList: List<Rate>): RecyclerView.Adapter<PopularAdapter.CurrencyViewHolder>() {

    inner class CurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(rate: Rate) {
            FragmentPopularRecyclerItemBinding.bind(itemView).also {
                it.currency.text = "${rate.rate} = "
                it.spot.text = rate.value.toString()
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