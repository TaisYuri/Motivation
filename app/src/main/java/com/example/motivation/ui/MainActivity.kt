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
import java.util.Locale

private lateinit var binding: ActivityMainBinding
private var categoryId = MotivationConstants.FILTER.ALL_INCLUSIVE


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //esconde a barra de navegação
        supportActionBar?.hide()

        //Eventos das imagens
        binding.imgAllInclusive.setOnClickListener(this)
        binding.imgHappy.setOnClickListener(this)
        binding.imgSunny.setOnClickListener(this)

        binding.buttonNewPhrase.setOnClickListener(this)

        //Clique no nome para voltar a tela de User
        binding.textWelcome.setOnClickListener(this)

        //seta a primeira imagem vir selecionada
        handleFilter(R.id.img_all_inclusive)

        //exibe primeira frase
        handleNextPhrase()
    }

    override fun onStart() {
        super.onStart()
        //Busca o nome do usuario
        showUserName()
    }
    override fun onClick(v: View) {
        if (v.id == R.id.buttonNewPhrase) {
            handleNextPhrase()

        } else if (v.id in listOf(
                R.id.img_all_inclusive,
                R.id.img_happy,
                R.id.img_sunny,
            )
        ) {
            handleFilter(v.id)
        }else if(v.id == R.id.text_welcome){
            startActivity(Intent(this, UserActivity::class.java))
        }

    }

    private fun showUserName() {
        //SharedPreferences (get)
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        val hello = getString(R.string.hello)
        binding.textWelcome.text = "${hello} $name!"
    }

    private fun handleNextPhrase() {
        val phrase = Mock().getPhrase(categoryId, Locale.getDefault().language)
        binding.textPhrase.text = phrase.description
        val category = getString(phrase.categoryId)
        binding.categoryPhrase.text = category

    }

    private fun handleFilter(id: Int) {
        binding.imgAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.img_background))
        binding.imgHappy.setColorFilter(ContextCompat.getColor(this, R.color.img_background))
        binding.imgSunny.setColorFilter(ContextCompat.getColor(this, R.color.img_background))

        when (id) {
            R.id.img_all_inclusive -> {
                binding.imgAllInclusive.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.ALL_INCLUSIVE
                handleNextPhrase()
            }
            R.id.img_happy -> {
                binding.imgHappy.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.HAPPY
                handleNextPhrase()
            }
            R.id.img_sunny -> {
                binding.imgSunny.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.FILTER.SUNNY
                handleNextPhrase()
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