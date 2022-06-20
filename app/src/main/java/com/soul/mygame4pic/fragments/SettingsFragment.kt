package com.soul.mygame4pic.fragments

import android.media.MediaPlayer
import android.util.Log
import com.soul.mygame4pic.utils.MyService
import com.soul.mygame4pic.R
import com.soul.mygame4pic.controller.Extensions
import com.soul.mygame4pic.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    val player: MyService = MyService()
    private var mediaPlayerClick: MediaPlayer? = null
    private var mediaPlayerWin: MediaPlayer? = null
    var mute: Boolean = false

    override fun onViewCreated() {

        mediaPlayerClick = MediaPlayer.create(requireContext(), R.raw.click_effect2)
        mediaPlayerWin = MediaPlayer.create(requireContext(), R.raw.winn_effect)
        mediaPlayerClick?.isLooping = false // Set looping
        mediaPlayerWin?.isLooping = false // Set looping
        mediaPlayerClick?.setVolume(100f, 100f)
        mediaPlayerWin?.setVolume(100f, 100f)

        binding.btnBackSettings.setOnClickListener {
            if (mute) {
                mediaPlayerClick?.start()
            } else {
                mediaPlayerClick?.stop()
            }
            Extensions.controller?.startMainFragment(MenuFragment())
        }

        binding.mute.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.d("TTTT", "TRUE")
                mute = true
                mediaPlayerClick?.start()

            } else {
                Log.d("TTTT", "FALSE")
                mute = false
                mediaPlayerClick?.pause()
            }
        }
    }

//    fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        mediaPlayer?.start()
//        return startId
//    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayerClick?.stop()
        mediaPlayerClick?.release()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayerClick?.stop()
    }

//    override fun onBackPressed(): Boolean {
//        val myCondition =false
//        return if (myCondition) {
//            //action not popBackStack
//            Toast.makeText(requireContext(), "working exit", Toast.LENGTH_SHORT).show()
//            true
//        } else {
//            false
//        }
//    }

//    override fun onPause() {
//        super.onPause()
//        stopService(Intent(this, MyService::class.java)) // остановить песню
//    }
//
//    // развернули приложение
//    override fun onResume() {
//        super.onResume()
//        startService(Intent(this, MyService::class.java)) // запустить песню
//    }

}