package com.soul.mygame4pic.fragments

import com.soul.mygame4pic.controller.Extensions
import com.soul.mygame4pic.databinding.FragmentInfoBinding

class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::inflate) {
    override fun onViewCreated() {
        binding.btnBackSettings.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
            Extensions.controller?.startMainFragment(MenuFragment())
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

}