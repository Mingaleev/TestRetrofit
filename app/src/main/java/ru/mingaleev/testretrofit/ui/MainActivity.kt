package ru.mingaleev.testretrofit.ui

import android.os.Bundle
import android.view.Menu
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

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PopularFragment()).commit()
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_menu_popular -> {
                    navigateTo(PopularFragment()); true
                }
                R.id.bottom_menu_favourites -> {
                    TODO()
                }
                else -> true
            }
        }
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
