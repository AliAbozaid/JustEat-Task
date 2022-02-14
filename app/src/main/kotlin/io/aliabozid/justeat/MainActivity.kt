package io.aliabozid.justeat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.aliabozid.justeat.assets.utils.viewBinding
import io.aliabozid.justeat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding
        by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.setBackgroundDrawableResource(R.color.background)
    }
}
