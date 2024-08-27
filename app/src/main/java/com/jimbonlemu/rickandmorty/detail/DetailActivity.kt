package com.jimbonlemu.rickandmorty.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.jimbonlemu.rickandmorty.R
import com.jimbonlemu.rickandmorty.base.BaseActivity
import com.jimbonlemu.rickandmorty.core.domain.model.Character
import com.jimbonlemu.rickandmorty.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    private val detailViewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.getParcelableExtra<Character>(CHAR_DATA)?.let {
            setCharacterData(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCharacterData(character: Character) {
        binding.apply {
            character.apply {
                Glide.with(this@DetailActivity).load(image).into(ivCharacter)
                tvCharName.text = name
                tvCharGender.text = "Gender : $gender"
                tvCharSpecies.text = "Species : $species"
                tvCharOriginName.text = "Origin : $originName"
                tvCharLocationName.text = "Location : $locationName"
                tvCharType.text = "Type : ${type.ifEmpty { "-" }}"
                tvCharStatus.text = "Status : $status"
                setFavorite(isFavorite)
                fabFavorite.setOnClickListener {
                    isFavorite = !isFavorite
                    detailViewModel.setFavoriteCharacter(character, isFavorite)
                    setFavorite(isFavorite)
                }
            }
        }

    }

    private fun setFavorite(isFavorite: Boolean) {
        binding.fabFavorite.apply {
            setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailActivity,
                    if (isFavorite) R.drawable.icon_favorited else R.drawable.icon_unfavorited
                )
            )
        }
    }

    override fun setupBinding(layoutInflater: LayoutInflater): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    companion object {
        const val CHAR_DATA = "char_data"
    }
}