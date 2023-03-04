package ru.mingaleev.testretrofit.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.mingaleev.testretrofit.R
import ru.mingaleev.testretrofit.data.dto.Currency
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

        val observer = Observer<List<Currency>> { newCurrenciesDTO ->
            binding.popularFragmentRecyclerView.adapter = PopularAdapter(newCurrenciesDTO)
        }
        viewModel.ratesList.observe(viewLifecycleOwner, observer)
        viewModel.getCurrencyList()
    }
}