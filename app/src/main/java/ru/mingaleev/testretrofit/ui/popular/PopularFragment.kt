package ru.mingaleev.testretrofit.ui.popular

import android.R.layout
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ru.mingaleev.testretrofit.MyApp
import ru.mingaleev.testretrofit.R
import ru.mingaleev.testretrofit.databinding.FragmentPopularBinding
import javax.inject.Inject

class PopularFragment @Inject constructor() : Fragment(R.layout.fragment_popular), AdapterView.OnItemSelectedListener {

    private var binding: FragmentPopularBinding? = null
    private var arrayAdapter: ArrayAdapter<String>? = null
    private var setSelection: Boolean = false
    private var arrayResource = mutableListOf<String>()

    @Inject
    lateinit var viewModel: PopularViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

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
        binding?.also {
            when (appStatePopular) {
                is AppStatePopular.SuccessListExchange -> {
                    it.popularFragmentRecyclerView.isVisible = true
                    it.popularFragmentRecyclerView.adapter =
                        PopularAdapter(appStatePopular.currenciesList, callbackAdd)
                    it.errorMessageTextView.isVisible = false
                    it.buttonUpdate.isVisible = false

                    if (arrayResource.isEmpty()) {
                        appStatePopular.currenciesList.forEach { currency ->
                            arrayResource.add(currency.name)
                        }
                        initSpinner()
                    }
                }
                is AppStatePopular.Error -> {
                    it.popularFragmentRecyclerView.isVisible = false
                    it.errorMessageTextView.isVisible = true
                    it.buttonUpdate.isVisible = true
                }
            }
        }
    }

    private val callbackAdd = AddItem {
        viewModel.addToDB(it)
    }

    private fun initSpinner() {
        arrayAdapter = ArrayAdapter(requireContext(), layout.simple_spinner_item, arrayResource)
        binding?.spinnerPopularFragment?.adapter = arrayAdapter
        binding?.spinnerPopularFragment?.onItemSelectedListener = this
        binding?.spinnerPopularFragment?.setSelection(0)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (setSelection) viewModel.getCurrencyList(arrayAdapter?.getItem(p2).toString()) else setSelection = true
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.buttonUpdate?.setOnClickListener(null)
    }
}