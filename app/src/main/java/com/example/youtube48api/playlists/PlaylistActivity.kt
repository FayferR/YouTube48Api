package com.example.youtube48api.playlists

import android.content.Intent
import android.net.ConnectivityManager
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube48api.core.network.Connection
import com.example.youtube48api.core.ui.BaseActivity
import com.example.youtube48api.data.model.Item
import com.example.youtube48api.databinding.ActivityInfoPlaylistBinding
import com.example.youtube48api.databinding.ActivityPlayslistBinding

class PlaylistActivity : BaseActivity<MainViewModel, ActivityPlayslistBinding>() {

    private lateinit var adapter: PlaylistAdapter

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityInfoPlaylistBinding {
        return ActivityInfoPlaylistBinding.inflate(layoutInflater)
    }

    private fun clickListener(id: String) {
        Intent(this, InfoPlaylistActivity::class.java).apply {
            putExtra(KEY_FOR_ID, id)
            startActivity(this)
        }
    }

    override fun initViewModel() {
        viewModel.playlist().observe(this) {
            if (it != null) {
                adapter = PlaylistAdapter(it.items as ArrayList<Item>, this::clickListener)
            }
            binding.ryPlaylists.adapter = adapter
        }
    }

    override fun checkInternet() {
        Connection((getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager))
            .observe(this) {
                binding.includedInternet.isVisible = !it
                binding.ryPlaylists.isVisible = it
                if (it == true) {
                    initViewModel()
                }
            }
    }

    companion object {
        val KEY_FOR_ID = "HELLO"
    }
}