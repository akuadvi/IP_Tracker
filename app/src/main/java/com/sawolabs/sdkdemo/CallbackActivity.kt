package com.sawolabs.sdkdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.sawolabs.androidsdk.LOGIN_SUCCESS_MESSAGE
import org.json.JSONObject

class CallbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_callback)

        val message = intent.getStringExtra(LOGIN_SUCCESS_MESSAGE)
        findViewById<TextView>(R.id.textView).apply {
            text = message}
            val buttonClick= findViewById<Button>(R.id.button)
        buttonClick.setOnClickListener(){
            intent = Intent(applicationContext, second::class.java)
            startActivity(intent)          }

    }


    }


