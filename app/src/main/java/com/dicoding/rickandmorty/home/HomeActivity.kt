package com.dicoding.rickandmorty.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.rickandmorty.base.BaseActivity
import com.dicoding.rickandmorty.core.data.utils.ResourceState
import com.dicoding.rickandmorty.core.ui.CharacterAdapter
import com.dicoding.rickandmorty.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        characterAdapter = CharacterAdapter()
        setupAdapter()
        setupObserver()
    }

    private fun setupObserver() {
        homeViewModel.character.observe(this@HomeActivity) { character ->
            if (character != null) {
                when (character) {
                    is ResourceState.Loading -> setupLoading(true)

                    is ResourceState.Success -> {
                        setupLoading(false)
                        characterAdapter.setData(character.data)
                    }

                    is ResourceState.Error -> {
                        setupLoading(false)
                        binding.tvError.apply {
                            visibility = View.VISIBLE
                            text = character.message ?: "Something went wrong"
                        }
                    }
                }
            }

        }
    }

    private fun setupAdapter() {
//        characterAdapter.onItemClick = {
//            startActivity(Intent(this@HomeActivity, DetailActivity::class.java))
//        }

        binding.rvCharacter.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = characterAdapter
        }

    }

    private fun setupLoading(isEnable: Boolean) {
        binding.apply {
            rvCharacter.visibility = if (isEnable) View.GONE else View.VISIBLE
            loader.visibility = if (isEnable) View.VISIBLE else View.GONE
            tvError.visibility = if (isEnable) View.GONE else tvError.visibility
        }
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

}