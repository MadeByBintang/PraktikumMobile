package com.example.listxml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listxml.ui.fragment.HomeFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())
        Timber.i("MainActivity created")

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, HomeFragment())
                .commit()
        }
    }
}