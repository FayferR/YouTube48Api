package com.example.youtube48api

import android.app.Application
import com.example.youtube48api.repository.Repository

class App : Application() {

    val repository: Repository by lazy {
        Repository()
    }
}