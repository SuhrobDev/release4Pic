package com.soul.mygame4pic

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.soul.mygame4pic.controller.Extensions
import com.soul.mygame4pic.databinding.ActivityMainBinding
import com.soul.mygame4pic.fragments.SplashFragment
import com.soul.mygame4pic.fragments.`interface`.IOnBackPressed

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Extensions.init(R.id.container, supportFragmentManager)
        Extensions.controller?.startMainFragment(SplashFragment())
//        mediaPlayer = MediaPlayer.create(this@MainActivity(), R.raw.gamemusic)
//        mediaPlayer?.isLooping = true // Set looping
//        mediaPlayer?.setVolume(100f, 100f)
    }

    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.container)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }

    override fun onPause() {
        super.onPause()

    }
}