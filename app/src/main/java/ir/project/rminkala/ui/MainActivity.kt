package ir.project.rminkala.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.ui.Home.ProductViewModel

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: ViewModel by viewModels()
    private val viewModelProduct: ProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_RminKala)
        setContentView(R.layout.activity_main)




        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.frag_host)
        bottomNavigationView.setupWithNavController(navController)

        preferencesInit()

    }
    private fun preferencesInit() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                val info = viewModel.preferences.first()
                val mode = info.theme.mode
                val currentMode = AppCompatDelegate.getDefaultNightMode()
                Log.d("Main", "preferencesInit: " + currentMode.toString())
                if (currentMode != mode) {
                    AppCompatDelegate.setDefaultNightMode(mode)
                }
            }
        }
    }
}