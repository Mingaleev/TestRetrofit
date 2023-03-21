package ru.mingaleev.testretrofit.ui.popular

import android.R.layout
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
import ru.mingaleev.testretrofit.databinding.FragmentPopularBinding
import javax.inject.Inject


class PopularFragment : DaggerFragment() {

    private var binding: FragmentPopularBinding? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var setSelection: Boolean = false
    private var arrayResource = mutableListOf<String>()
    private var baseCurrency = "AED"

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<PopularViewModel> { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPopularBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.ratesList.observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding?.buttonUpdate?.setOnClickListener {
            viewModel.getCurrencyList("AED")
        }

        initSpinner()
    }

    private fun renderData(appStatePopular: AppStatePopular) {
        binding?.also { bind ->
            when (appStatePopular) {
                is AppStatePopular.SuccessListExchange -> {
                    bind.popularFragmentRecyclerView.isVisible = true
                    bind.popularFragmentRecyclerView.adapter =
                        PopularAdapter(appStatePopular.currenciesList, callbackAdd)
                    bind.errorMessageTextView.isVisible = false
                    bind.buttonUpdate.isVisible = false

                    if (arrayResource.isEmpty()) {
                        appStatePopular.currenciesList.forEach {arrayResource.add(it.name) }
                        initSpinner()
                    }
                }
                is AppStatePopular.Error -> {
                    bind.popularFragmentRecyclerView.isVisible = false
                    bind.errorMessageTextView.isVisible = true
                    bind.buttonUpdate.isVisible = true
                }
            }
        }
    }

    private val callbackAdd = AddItem {
        viewModel.addToDB(it)
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
        arrayAdapter = ArrayAdapter(requireContext(), layout.simple_spinner_item, arrayResource)
        binding?.spinnerPopularFragment?.adapter = arrayAdapter
        binding?.spinnerPopularFragment?.onItemSelectedListener = onItemSelectedListener
        binding?.spinnerPopularFragment?.setSelection(0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.buttonUpdate?.setOnClickListener(null)
        binding?.spinnerPopularFragment?.onItemSelectedListener = null
        binding?.spinnerPopularFragment?.adapter = null
    }
}