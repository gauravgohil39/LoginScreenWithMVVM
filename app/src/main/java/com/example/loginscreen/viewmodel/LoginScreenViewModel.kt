package com.example.loginscreen.viewmodel

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginscreen.Repository.LoginScreenRepository
import com.example.loginscreen.pojo.UserCredentials
import com.example.loginscreen.util.toast
import java.util.regex.Pattern

class LoginScreenViewModel : ViewModel() {

    private var loginResponse: MutableLiveData<Boolean> = MutableLiveData(false)
    private var failedMessage: String = "empty"

    // defining our own password pattern
    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{6,}" +  // at least 6 characters
                "$"
    )

    fun isAuthunticationCompleted(): MutableLiveData<Boolean> {

        return loginResponse
    }

    fun authunticateUserCredentials(
        email: String,
        password: String,
        context: Context
    ) {


        Log.e(
            "Developer",
            "authunticateUserCredentials :  Email entered is $email  and  Password entered is $password"
        )


        if (validateEmailAddress(email, context) && validatePassword(password, context)) {
            val usercred: MutableLiveData<UserCredentials> =
                LoginScreenRepository.getUserDetails(context)

            var email_return_value = usercred.value?.username
            var password_return_value = usercred.value?.passcode

            Log.e(
                "Developer",
                " authunticateUserCredentials : Email return value is $email_return_value"
            )
            Log.e(
                "Developer",
                " authunticateUserCredentials : Password return value is $password_return_value"
            )

            if (email_return_value.equals(email, true) && password_return_value.equals(
                    password,
                    true
                )
            ) {
                failedMessage = "Login Successfull"
                loginResponse.value = true
            } else
                failedMessage = "Email or password is Mis-Match"


            context.toast(failedMessage)


        }


    }


    private fun validateEmailAddress(email: String, context: Context): Boolean {

        val user_email = email.trim()

        if (user_email.isNullOrEmpty()) {
            failedMessage = "Email is required!"
            context.toast(failedMessage)

            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
            failedMessage = "Enter a valid email"

            context.toast(failedMessage)

            return false

        }

        return true
    }

    private fun validatePassword(password: String, context: Context): Boolean {

        val user_passcode = password.trim()

        if (user_passcode.isEmpty()) {
            failedMessage = "Password is required!"
            context.toast(failedMessage)

            return false
        }

        if (user_passcode.length < 6) {
            failedMessage = "Password should be atleast 6 character long"
            context.toast(failedMessage)
            return false
        }

        if (!PASSWORD_PATTERN.matcher(user_passcode).matches()) {
            failedMessage =
                "Password must be contain symbol(*,&,\$,#,@,!), charter(a-z & A-Z) and number(0-9) "
            context.toast(failedMessage)
            return false
        }

        return true
    }


}