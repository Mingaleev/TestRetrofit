package ru.mingaleev.testretrofit.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mingaleev.testretrofit.R
import ru.mingaleev.testretrofit.databinding.FragmentPopularBinding

class PopularFragment : Fragment(R.layout.fragment_popular) {

    private var binding: FragmentPopularBinding? = null

    private val viewModel by lazy {
        ViewModelProvider(this)[PopularViewModel::class.java]
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
            viewModel.getCurrencyList()
        }
    }

    private fun renderData(appStatePopular: AppStatePopular) {
        binding?.also {
            when (appStatePopular) {
                is AppStatePopular.SuccessListExchange -> {
                    it.popularFragmentRecyclerView.isVisible = true
                    it.popularFragmentRecyclerView.adapter =
                        PopularAdapter(appStatePopular.currenciesList)
                    it.errorMessageTextView.isVisible = false
                    it.buttonUpdate.isVisible = false
                }
                is AppStatePopular.Error -> {
                    it.popularFragmentRecyclerView.isVisible = false
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