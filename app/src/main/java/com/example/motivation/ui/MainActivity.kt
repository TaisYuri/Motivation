package com.example.motivation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.databinding.ActivityUserBinding

private lateinit var binding: ActivityMainBinding
private var categoryId = MotivationConstants.FILTER.ALL_INCLUSIVE


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //esconde a barra de navegação
        supportActionBar?.hide()

        //SharedPreferences (get)
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textWelcome.text = "Olá $name!"

        binding.buttonNewPhrase.setOnClickListener(this)


        //Eventos das imagens
        binding.imgAllInclusive.setOnClickListener(this)
        binding.imgHappy.setOnClickListener(this)
        binding.imgSunny.setOnClickListener(this)
        binding.imgExitToApp.setOnClickListener(this)

        //seta a primeira imagem vir selecionada
        handleFilter(R.id.img_all_inclusive)

        //exibe primeira frase
        handleNextPhrase()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.buttonNewPhrase) {
            handleNextPhrase()

        } else if (v.id in listOf(
                R.id.img_all_inclusive,
                R.id.img_happy,
                R.id.img_sunny,
                R.id.img_exit_to_app
            )
        ) {
            handleFilter(v.id)
        }

    }

    private fun handleNextPhrase() {
        val phrase = Mock().getPhrase(categoryId)
        binding.textPhrase.text = phrase
    }

    private fun handleFilter(id: Int) {
        binding.imgAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.img_background))
        binding.imgHappy.setColorFilter(ContextCompat.getColor(this, R.color.img_background))
        binding.imgSunny.setColorFilter(ContextCompat.getColor(this, R.color.img_background))
        binding.imgExitToApp.setColorFilter(ContextCompat.getColor(this, R.color.img_background))

        when (id) {
            R.id.img_all_inclusive -> {
                binding.imgAllInclusive.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.ALL_INCLUSIVE
            }
            R.id.img_happy -> {
                binding.imgHappy.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.img_sunny -> {
                binding.imgSunny.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.SUNNY
            }
            R.id.img_exit_to_app -> {
                binding.imgExitToApp.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                handleSignOut() //EFETUAR LOGOFF
            }
        }

    }

    private fun handleSignOut() {
        SecurityPreferences(this).removeString(MotivationConstants.KEY.USER_NAME)
        Toast.makeText(this, "Logoff efetuado", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }


}