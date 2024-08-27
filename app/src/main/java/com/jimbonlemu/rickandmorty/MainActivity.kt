package com.jimbonlemu.rickandmorty

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import com.jimbonlemu.rickandmorty.base.BaseActivity
import com.jimbonlemu.rickandmorty.databinding.ActivityMainBinding
import com.jimbonlemu.rickandmorty.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)
}