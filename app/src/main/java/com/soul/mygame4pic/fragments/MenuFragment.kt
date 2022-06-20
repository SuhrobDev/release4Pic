package com.soul.mygame4pic.fragments

import android.media.MediaPlayer
import android.util.Log
import com.soul.mygame4pic.managers.GameManager
import com.soul.mygame4pic.managers.Questions
import com.soul.mygame4pic.shared.Shared
import com.soul.mygame4pic.R
import com.soul.mygame4pic.controller.Extensions
import com.soul.mygame4pic.databinding.FragmentMenuBinding

class MenuFragment : BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate) {
    private val shared by lazy {
        Shared(requireContext())
    }
    private val question by lazy {
        Questions()
    }
    lateinit var gameManager: GameManager
    private var mediaPlayerClick: MediaPlayer? = null

    override fun onViewCreated() {
        gameManager = GameManager(
            question.getQuestions(),
            shared.getLevel(),
            shared.getCoin(),
            shared.getHelper(),
            shared.getChance()
        )
        mediaPlayerClick = MediaPlayer.create(requireContext(), R.raw.click_effect2)
        mediaPlayerClick?.isLooping = false // Set looping
        mediaPlayerClick?.setVolume(100f, 100f)
        binding.btnPlay.setOnClickListener {
            mediaPlayerClick?.start()
            Extensions.controller?.replaceFragment(GameFragment())
        }

        binding.btnInfo.setOnClickListener {
            mediaPlayerClick?.start()
            Extensions.controller?.replaceFragment(InfoFragment())
        }

        binding.btnSettings.setOnClickListener {
            mediaPlayerClick?.start()
            Extensions.controller?.replaceFragment(SettingsFragment())
        }

        Log.d("hhh", "onCreate: ${shared.getLevel()}")
        binding.coin.text = shared.getCoin().toString()

        binding.lastQue.text = shared.getLevel().toString()

        binding.image.setImageResource(gameManager.questionsList[shared.getLevel()].image)
        if (mediaPlayerClick?.isPlaying != true) {
            mediaPlayerClick?.pause()
        } else {
            mediaPlayerClick?.start()
        }
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
//    fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        mediaPlayerClick?.start()
//        return startId
//    }
//    var backPressedTime: Long = 0
//    override fun onBackPressed() {
//        if (backPressedTime + 3000 > System.currentTimeMillis()) {
//            super.onBackPressed()
//            finish()
//        } else {
//            Toast.makeText(this, "press again to exit", Toast.LENGTH_LONG).show()
//        }
//        backPressedTime = System.currentTimeMillis()
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