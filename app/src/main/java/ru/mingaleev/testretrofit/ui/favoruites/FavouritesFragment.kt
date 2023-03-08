package ru.mingaleev.testretrofit.ui.favoruites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mingaleev.testretrofit.R
import ru.mingaleev.testretrofit.databinding.FragmentFavouritesBinding
import ru.mingaleev.testretrofit.databinding.FragmentPopularBinding

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private var binding: FragmentFavouritesBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this)[FavouritesViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavouritesBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.ratesList.observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding?.buttonUpdate?.setOnClickListener {
            viewModel.getCurrencyList()
        }
    }

    private fun renderData(appStateFavourites: AppStateFavourites) {
        binding?.also {
            when (appStateFavourites) {
                is AppStateFavourites.SuccessListExchange -> {
                    it.favouritesFragmentRecyclerView.isVisible = true
                    it.favouritesFragmentRecyclerView.adapter =
                        FavouritesAdapter(appStateFavourites.currenciesList)
                    it.errorMessageTextView.isVisible = false
                    it.buttonUpdate.isVisible = false
                }
                is AppStateFavourites.Error -> {
                    it.favouritesFragmentRecyclerView.isVisible = false
                    it.errorMessageTextView.isVisible = true
                    it.buttonUpdate.isVisible = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.buttonUpdate?.setOnClickListener(null)
    }
}