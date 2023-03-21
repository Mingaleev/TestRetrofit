package ru.mingaleev.testretrofit.ui.favoruites

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.android.support.DaggerFragment
import ru.mingaleev.testretrofit.databinding.FragmentFavouritesBinding
import ru.mingaleev.testretrofit.di.viewModel.ViewModelFactory
import javax.inject.Inject

class FavouritesFragment : DaggerFragment() {

    private var binding: FragmentFavouritesBinding? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var setSelection: Boolean = false
    private var arrayResource = mutableListOf<String>()
    private var baseCurrency = "AED"

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<FavouritesViewModel> { viewModelFactory }

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
            viewModel.getCurrencyList(baseCurrency)
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
                    it.spinnerPopularFragment.adapter
                    if (arrayResource.isEmpty()) {
                        arrayResource = appStateFavourites.listForSpinner.toMutableList()
                        initSpinner()
                    }
                }
                is AppStateFavourites.Error -> {
                    it.favouritesFragmentRecyclerView.isVisible = false
                    it.errorMessageTextView.isVisible = true
                    it.buttonUpdate.isVisible = true
                }
            }
        }
    }

    private val onItemSelectedListener: AdapterView.OnItemSelectedListener by lazy {
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                baseCurrency = arrayAdapter?.getItem(selectedItemPosition).toString()
                if (setSelection) viewModel.getCurrencyList(
                    baseCurrency
                ) else setSelection = true
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun initSpinner() {
        arrayAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, arrayResource)
        binding?.spinnerPopularFragment?.adapter = arrayAdapter
        binding?.spinnerPopularFragment?.onItemSelectedListener = onItemSelectedListener
        binding?.spinnerPopularFragment?.setSelection(0)
    }

    private val callbackAdd = RemoveItem {
        viewModel.removeInDB(it)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.buttonUpdate?.setOnClickListener(null)
    }
}