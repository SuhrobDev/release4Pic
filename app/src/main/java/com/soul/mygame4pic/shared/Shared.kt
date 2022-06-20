package com.soul.mygame4pic.shared

import android.content.Context
import android.content.SharedPreferences
import java.util.ArrayList

class Shared(context:Context) {

    private var preferences: SharedPreferences =
        context.getSharedPreferences("APP_PREFS_NAME", Context.MODE_PRIVATE)

    private lateinit var editor: SharedPreferences.Editor

    fun setLevel(level:Int){
        editor = preferences.edit()
        editor.putInt("LEVEL",level)
        editor.apply()
    }

    fun getLevel() = preferences.getInt("LEVEL",1)

    fun setCoin(coin:Int){
        editor = preferences.edit()
        editor.putInt("COIN",coin)
        editor.apply()
    }

    fun getCoin() = preferences.getInt("COIN",20)

    fun setChance(chance:Int){
        editor = preferences.edit()
        editor.putInt("CHANCE",chance)
        editor.apply()
    }

    fun getChance() = preferences.getInt("CHANCE",5)

    fun setHelper(helper:Int){
        editor = preferences.edit()
        editor.putInt("HELP",helper)
        editor.apply()
    }

    fun getHelper() = preferences.getInt("HELP",5)

//    fun getHelper() = preferences.getInt("HELPER",0)
//
//    fun setWord(letters : ArrayList<String>){
//        editor = preferences.edit()
//
//        letters.forEachIndexed { i, s ->
//            editor.putString("WORD_LETTER_$i",s)
//        }
//
//        editor.apply()
//    }
//
//    fun getWord():ArrayList<String>{
//        var letter = ArrayList<String>()
//
//        for (i in 0 until 6){
//            if (letter[i].isEmpty()){
//                letter.add(preferences.getString("GET_WORD_$i","").toString())
//            }else{
//                letter.add(preferences.getString("GET_WORD_$i",letter[i]).toString())
//            }
//        }
//        return letter
//    }
}