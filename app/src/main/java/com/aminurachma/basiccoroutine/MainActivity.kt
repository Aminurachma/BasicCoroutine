package com.aminurachma.basiccoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val scope = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnClick = findViewById<Button>(R.id.btn_click)
        btnClick.setOnClickListener {
            scope.launch {
                longRunningTask()
            }
        }

    }

    suspend fun longRunningTask() {
        delay(2000)
        Log.d("${Thread.currentThread().name}", "Response")
//            val textResult = findViewById<TextView>(R.id.textResult)
//                textResult.text = textResult.text.toString()+"\n"+"Response"
        printUIData("Response")
    }

    suspend fun printUIData(message: String){
        withContext(Dispatchers.Main){
            val textResult = findViewById<TextView>(R.id.textResult)
            textResult.text = textResult.text.toString()+"\n"+message
        }
    }
}