package com.sawolabs.sdkdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sawolabs.androidsdk.Sawo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickLogin(view: View) {
        Sawo(
            this,
            "661a94e4-1e43-41ee-abcb-4350e206e8a8", // your api key,
            "60715a90c945b25e88fdf83bIZrVqZhW1H4xG51UuXWUyxgG" // your secret key
        ).login(
            "email", // can be one of 'email' or 'phone_number_sms'
            second::class.java.name // Callback class name
        )
    }
}