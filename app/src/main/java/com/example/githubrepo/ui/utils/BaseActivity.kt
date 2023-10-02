package com.example.githubrepo.ui.utils

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.githubrepo.R
import com.example.githubrepo.utils.DARK_MODE
import com.example.githubrepo.utils.Network
import com.example.githubrepo.utils.SHARED_PREFERENCE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseActivity : ComponentActivity() {

    var darkModeState by mutableStateOf(false)
    var connectionState by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shared = baseContext.getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE)
        darkModeState = shared.getBoolean(DARK_MODE, false)

    }

    override fun onResume() {
        super.onResume()
        if (!Network.hasInternet(baseContext)) {
            println("here")
            connectionState = false
            Toast.makeText(
                baseContext, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun setDarkMode(value: Boolean) {
        darkModeState = value
        val shared = baseContext.getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE)
        shared.edit().putBoolean(DARK_MODE, darkModeState).apply()
    }
}