package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityUserBinding

private lateinit var binding: ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        //Verifica se possui algum nome no sharedPreferences
        //verifyUser()

        binding.buttonSave.setOnClickListener(this)
    }

    private fun verifyUser() {
        //Verifica se possui name salvo no storage, caso tenha, exibe a mainActivity
        val name = SecurityPreferences(this).getString( MotivationConstants.KEY.USER_NAME)
        if(name !== ""){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editYourName.text.toString()
        if (name != "") {

            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)

            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}