package ru.mingaleev.testretrofit.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.mingaleev.testretrofit.R
import ru.mingaleev.testretrofit.databinding.FragmentPopularBinding

class PopularFragment : Fragment(R.layout.fragment_popular) {

    private lateinit var binding: FragmentPopularBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[PopularViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPopularBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.ratesList.observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.getCurrencyList()

        binding.buttonUpdate.setOnClickListener {
            viewModel.getCurrencyList()
        }
    }

    private fun renderData(appStatePopular: AppStatePopular) {
        when (appStatePopular) {
            is AppStatePopular.SuccessListExchange -> {
                binding.popularFragmentRecyclerView.visibility = View.VISIBLE
                binding.popularFragmentRecyclerView.adapter =
                    PopularAdapter(appStatePopular.currenciesList)
                binding.errorMessageTextView.visibility = View.GONE
                binding.buttonUpdate.visibility = View.GONE
            }
            is AppStatePopular.Error -> {
                binding.popularFragmentRecyclerView.visibility = View.GONE
                binding.errorMessageTextView.visibility = View.VISIBLE
                binding.buttonUpdate.visibility = View.VISIBLE
            }
        }
    }
}