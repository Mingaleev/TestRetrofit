package ru.mingaleev.testretrofit.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.favoruites.ui.FavouritesFragment
import com.example.populars.ui.PopularFragment
import ru.mingaleev.testretrofit.R
import ru.mingaleev.testretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PopularFragment()).commit()
        }

        binding?.bottomNavigationView?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_menu_popular -> {
                    navigateTo(PopularFragment()); true
                }
                R.id.bottom_menu_favourites -> {
                    navigateTo(FavouritesFragment()); true
                }
                else -> true
            }
        }
    }

    @SuppressLint("CommitTransaction")
    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
