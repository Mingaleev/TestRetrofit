package ru.mingaleev.testretrofit.ui.favoruites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import dagger.android.support.DaggerFragment
import ru.mingaleev.testretrofit.databinding.FragmentFavouritesBinding
import javax.inject.Inject

class FavouritesFragment : DaggerFragment() {

    private var binding: FragmentFavouritesBinding? = null

    @Inject
    lateinit var viewModel: FavouritesViewModel

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
                        FavouritesAdapter(appStateFavourites.currenciesList, callbackAdd)
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

    private val callbackAdd = RemoveItem {
        viewModel.removeInDB(it)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.buttonUpdate?.setOnClickListener(null)
    }
}