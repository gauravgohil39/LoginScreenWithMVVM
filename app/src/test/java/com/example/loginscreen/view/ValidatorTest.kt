package com.example.loginscreen.view

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatorTest {

    @Test
    fun emptyInput() {

        val email = ""
        val password = ""

        val result = Validator.verificationOfEmailAndPassword(email, password)

        assertThat(result).isFalse()

    }

    @Test
    fun whenInputIsValidButNotTrue() {

        val email = "gohil@gmail.com"
        val passowrd = "sam@123"

        val result = Validator.validateInput(email, passowrd)

        assertThat(result).isEqualTo(false)

    }
    @Test
    fun whenInputIsValid() {

        val email = "gaurav@gmail.com"
        val passowrd = "sam@123"

        val result = Validator.validateInput(email, passowrd)

        assertThat(result).isEqualTo(true)

    }

    @Test
    fun whenInputIsInvalid() {

        val email = "gaurav.gohil"
        val passowrd = "sam"

        val result = Validator.validateInput(email, passowrd)

        assertThat(result).isEqualTo(false)
    }


    @Test
    fun crossVerifyEmailAndPasswordWithServer_Success() {
        val email = "gaurav@gmail.com"
        val passowrd = "sam@123"

        val result = Validator.verificationOfEmailAndPassword(email, passowrd)
        assertThat(result).isEqualTo(true)

    }

    @Test
    fun crossVerifyEmailAndPasswordWithServer_UnSuccess() {
        val email = "gaurav.gohil"
        val passowrd = "sam"

        val result = Validator.verificationOfEmailAndPassword(email, passowrd)
        assertThat(result).isEqualTo(false)

    }
}