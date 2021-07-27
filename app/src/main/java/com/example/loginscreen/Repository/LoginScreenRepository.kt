package com.example.loginscreen.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.loginscreen.pojo.UserCredentials
import org.json.JSONObject
import java.io.InputStream


object LoginScreenRepository {


    //  private lateinit var userDetailsLiveData: MutableLiveData<UserCredentials>

    fun getUserDetails(context: Context): MutableLiveData<UserCredentials> {

        //     userDetailsLiveData = checkUserCredentials(context)


        return checkUserCredentials(context)
    }

    private fun checkUserCredentials(context: Context): MutableLiveData<UserCredentials> {

        var userverified: MutableLiveData<UserCredentials> = MutableLiveData()

        try {

            val inputStream: InputStream = context.assets.open("userdetails.json")
            var json = inputStream.bufferedReader().use { it.readText() }
            Log.e("Developer", "json : $json")

            val jsonObject = JSONObject(json)

            val username = jsonObject.getString("email")
            val passcode = jsonObject.getString("passcode")

            Log.e("Developer", "username : $username")
            Log.e("Developer", "passcode : $passcode")


            userverified.value = UserCredentials(username, passcode)

            return userverified

        } catch (exception: Exception) {
            Log.e("Developer", "exception : $exception")
        }

        userverified.value = UserCredentials()

        return userverified
    }


}