package com.jimbonlemu.rickandmorty.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimbonlemu.rickandmorty.base.BaseActivity
import com.jimbonlemu.rickandmorty.core.ui.CharacterAdapter
import com.jimbonlemu.rickandmorty.detail.DetailActivity
import com.jimbonlemu.rickandmorty.di.FavoriteModuleDependencies
import com.jimbonlemu.rickandmorty.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder().context(this).appDependencies(
            EntryPointAccessors.fromApplication(
                applicationContext,
                FavoriteModuleDependencies::class.java
            )
        ).build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        characterAdapter = CharacterAdapter()
        setupAdapter()
        setupObserver()

    }

    private fun setupObserver() {
        favoriteViewModel.character.observe(this) { listChar ->
            characterAdapter.setData(listChar)
            val isEmpty = listChar.isNullOrEmpty()
            if (isEmpty) {
                Toast.makeText(this, "You don't favorite any favorite character at all", Toast.LENGTH_SHORT)
                    .show()
            }
            binding.rvFavorite.visibility = if (isEmpty) View.GONE else View.VISIBLE
        }
    }


    private fun setupAdapter() {
        characterAdapter.onItemClick = { data ->
            startActivity(
                Intent(this@FavoriteActivity, DetailActivity::class.java).putExtra(
                    DetailActivity.CHAR_DATA,
                    data
                )
            )
        }
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = characterAdapter
        }

    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityFavoriteBinding =
        ActivityFavoriteBinding.inflate(layoutInflater)
}