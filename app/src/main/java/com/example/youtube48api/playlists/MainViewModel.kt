package com.example.youtube48api.playlists

import androidx.lifecycle.LiveData
import com.example.youtube48api.App
import com.example.youtube48api.core.ui.BaseViewModel
import com.example.youtube48api.data.model.Playlists

class MainViewModel : BaseViewModel() {


    fun playlist(): LiveData<Playlists> {
        return App().repository.getPlaylists()
    }
}