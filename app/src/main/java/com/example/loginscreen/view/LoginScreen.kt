package com.example.loginscreen.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.loginscreen.databinding.LoginScreenBinding
import com.example.loginscreen.viewmodel.LoginScreenViewModel

class LoginScreen : AppCompatActivity() {

    private lateinit var binding: LoginScreenBinding
    private lateinit var viewModel: LoginScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(LoginScreenViewModel::class.java)


        binding.login.setOnClickListener {

            val email = binding.edittextUsername.text.toString()
            val password = binding.edittextPassword.text.toString()

            Log.e("Developer", "binding.login: Email entered is $email  and  Password entered is $password")

            viewModel.authunticateUserCredentials(email, password, this)

            viewModel.isAuthunticationCompleted().observe(this, {

                if (it) {
                    startActivity(
                        Intent(
                            applicationContext, HomeScreen::class.java
                        )
                    )

                    finish()

                }
            })

        }


    }


}