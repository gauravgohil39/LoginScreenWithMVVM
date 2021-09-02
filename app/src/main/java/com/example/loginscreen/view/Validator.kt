package com.example.loginscreen.view

import java.util.regex.Pattern

object Validator {

    // defining our own password pattern
    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\S+$)" +  // no white spaces
                ".{6,}" +  // at least 6 characters
                "$"
    )

    private val EMAIL_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )


    val username_list = listOf("kumar@gmail.com", "gohil@gmail.com")


    fun validateInput(email: String, password: String): Boolean {

        if (email.isEmpty() || password.isEmpty()) {
            return false
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return false
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return false
        }
        if (email in username_list)
            return false

        return true
    }

    fun verificationOfEmailAndPassword(email: String, password: String): Boolean {

        // Acutual email address and password from server or db or json
        val username = "gaurav@gmail.com"
        val passcode = "sam@123"

        return username.equals(email, true) && passcode.equals(password, true)

    }

}