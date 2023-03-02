package ru.mingaleev.testretrofit.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.mingaleev.testretrofit.R
import ru.mingaleev.testretrofit.databinding.ActivityMainBinding
import ru.mingaleev.testretrofit.ui.popular.PopularFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_menu_popular -> {
                    navigateTo(PopularFragment()); true
                }
                R.id.bottom_menu_favourites -> {
                    TODO()
//                    navigateTo(EarthFragment()); true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_menu_popular
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}

//    private val viewModel by lazy {
//        ViewModelProvider(this)[MainActivityViewModel::class.java]
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val textView: TextView = findViewById(R.id.text_view)
//
//        val observer = Observer<CurrenciesDTO> { newCur ->
//            textView.text = newCur.rates.toString()
//        }
//        viewModel.ratesList.observe(this, observer)
//        viewModel.getCurrencyList()
//    }
