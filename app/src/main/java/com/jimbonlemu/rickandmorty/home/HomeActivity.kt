package com.jimbonlemu.rickandmorty.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimbonlemu.rickandmorty.R
import com.jimbonlemu.rickandmorty.base.BaseActivity
import com.jimbonlemu.rickandmorty.core.data.utils.ResourceState
import com.jimbonlemu.rickandmorty.core.ui.CharacterAdapter
import com.jimbonlemu.rickandmorty.databinding.ActivityHomeBinding
import com.jimbonlemu.rickandmorty.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        characterAdapter = CharacterAdapter()
        setupAdapterAndRv()
        setupObserver()
        setupToolBar()

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

    private fun setupAdapterAndRv() {
        characterAdapter.onItemClick = { data ->
            startActivity(
                Intent(this@HomeActivity, DetailActivity::class.java).putExtra(
                    DetailActivity.CHAR_DATA,
                    data
                )
            )
        }

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

    private fun setupToolBar() {
        binding.abToolbar.setOnMenuItemClickListener { itemClicked ->
            when (itemClicked.itemId) {
                R.id.menuToFavorite -> {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("rickandmorty://favorite")))
                    true
                }

                else -> false
            }

        }
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

}