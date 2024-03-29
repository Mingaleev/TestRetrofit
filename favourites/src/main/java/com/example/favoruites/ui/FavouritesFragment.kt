package com.example.favoruites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.core.di.viewModel.ViewModelFactory
import dagger.android.support.DaggerFragment
import android.R.layout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.favoruites.R
import com.example.favoruites.databinding.BottomSheetBinding
import com.example.favoruites.databinding.FragmentFavouritesBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
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

    private val onItemSelectedListener: AdapterView.OnItemSelectedListener by lazy {
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                baseCurrency = arrayAdapter?.getItem(selectedItemPosition).toString()
                if (setSelection) viewModel.getCurrencyList(baseCurrency) else setSelection = true
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
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
            viewModel.getCurrencyList(baseCurrency)
        }

        initBottomSheetSort()
    }

    private fun initBottomSheetSort () {
        binding?.sort?.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.bottom_sheet, null)

            dialog.setContentView(view)
            dialog.show()
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

    private fun initSpinner() {
        arrayAdapter = ArrayAdapter(requireContext(), layout.simple_spinner_item, arrayResource)
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
        binding?.spinnerPopularFragment?.onItemSelectedListener = null
        binding?.spinnerPopularFragment?.adapter = null
    }
}