package com.uq.test.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.uq.test.R


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this@MainActivity, MainActivityViewModelFactory()).get(MainActivityViewModel::class.java)
    }
}