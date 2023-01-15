package com.example.youtube48api.repository

import com.example.youtube48api.BuildConfig
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.youtube48api.core.network.RetrofitClient
import com.example.youtube48api.data.model.Playlists
import com.example.youtube48api.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylists(): LiveData<Playlists> {
        val data = MediatorLiveData<Playlists>()

        apiService.getPlaylists("snippet,contentDetails", "UCshNYNzkggNqtKD09vLq3SQ",
            BuildConfig.API_KEY, 30)
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                }
            })
        return data
    }
}