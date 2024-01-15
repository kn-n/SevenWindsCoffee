package ru.kn_n.sevenwindscoffee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.yandex.mapkit.MapKitFactory
import ru.kn_n.sevenwindscoffee.databinding.ActivityMainBinding
import ru.kn_n.sevenwindscoffee.utils.constants.MAP_KEY

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!

    private val navHostFragment by lazy {
        binding.container.getFragment<NavHostFragment>()
    }

    private val navController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        MapKitFactory.setApiKey(MAP_KEY)
        MapKitFactory.initialize(this)
        setContentView(binding.root)

        setupToolBar()
    }

    private fun setupToolBar(){

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.registerFragment,
                R.id.loginFragment,
                R.id.cafesFragment
            )
        )
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}