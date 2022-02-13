package io.aliabozid.justeat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import io.aliabozid.justeat.assets.utils.viewBinding
import io.aliabozid.justeat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        (supportFragmentManager.findFragmentById(R.id.container) as? NavHostFragment)?.let {
            navController = it.navController
        }
        window.setBackgroundDrawableResource(R.color.background)
        // Going to Edge to Edge
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}
