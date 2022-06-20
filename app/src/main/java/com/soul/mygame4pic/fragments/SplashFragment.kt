package com.soul.mygame4pic.fragments

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import com.soul.mygame4pic.controller.Extensions
import com.soul.mygame4pic.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val handler = Handler(Looper.getMainLooper())
    private var i = 0
    private var t = 0

    override fun onViewCreated() {
        progress()
    }

    @SuppressLint("SetTextI18n")
    private fun progress() {
        i = binding.progressBar.progress
        binding.progressText.text = "$t"
        Thread {
            while (i < 100) {
                i += 1
                t += 1
                handler.post {
                    binding.progressBar.progress = i
                    binding.progressText.text = "$t%"
                }
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
//            binding.progressBar.visibility = View.INVISIBLE
            Extensions.controller?.startMainFragment(MenuFragment())
        }.start()
    }

//    private fun allCheck(): Boolean {
//        if (progressStatus == maxProgress) {
//            return true
//        }
//        return false
//    }
//
//    private fun count() {
//        progressStatus += 1
//        Thread.sleep(25)
//    }

}