package com.soul.mygame4pic.fragments

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import com.soul.mygame4pic.R
import com.soul.mygame4pic.controller.Extensions
import com.soul.mygame4pic.databinding.FragmentGameBinding
import com.soul.mygame4pic.managers.GameManager
import com.soul.mygame4pic.managers.Questions
import com.soul.mygame4pic.shared.Shared
import com.soul.mygame4pic.utils.gone
import com.soul.mygame4pic.utils.invisible
import com.soul.mygame4pic.utils.isInvisible
import com.soul.mygame4pic.utils.visible
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.DialogAnimation
import com.thecode.aestheticdialogs.DialogStyle
import com.thecode.aestheticdialogs.DialogType

class GameFragment : BaseFragment<FragmentGameBinding>(FragmentGameBinding::inflate) {

    private lateinit var lettersList: ArrayList<AppCompatButton>
    private lateinit var imagesList: ArrayList<ImageView>
    lateinit var wordList: ArrayList<AppCompatButton>
    lateinit var gameManager: GameManager
    var string = ""

    private val question by lazy {
        Questions()
    }

    private val shared by lazy {
        Shared(requireContext())
    }

    override fun onViewCreated() {
        gameManager = GameManager(
            question.getQuestions(),
            shared.getLevel(),
            shared.getCoin(),
            shared.getHelper(),
            shared.getChance()
        )
        Log.d("ssss", "onCreate: ${shared.getLevel()}  ${shared.getHelper()}  ${shared.getCoin()}")
        loadViews()
        loadDataToView()

        binding.coin.text = shared.getCoin().toString()
        binding.level.text = gameManager.level.toString()
        binding.chance.text= shared.getChance().toString()
        binding.btnBack.setOnClickListener {
            Extensions.controller?.startMainFragment(MenuFragment())
        }

        binding.btnClear.setOnClickListener {
            clear()
        }

//        binding.btnHelp.setOnClickListener {
//            if (gameManager.help >= 1) binding.btnHelp.setImageResource(R.drawable.ic_help)
//            else if (gameManager.help == 0) binding.btnHelp.setImageResource(R.drawable.ic_coins)
//
//            var grow = 4
//            for (groww: Int in 4..grow step 2) {
//                grow = groww
//                grow += 2
//            }
//
//            if (gameManager.help <= 0) {
//                if (gameManager.coins >= grow) {
//                    gameManager.coins -= grow
//                    binding.coin.text = gameManager.coins.toString()
//                    shared.setHelper(gameManager.help)
//                    shared.setCoin(gameManager.coins)
//                    help()
//                } else Toast.makeText(requireContext(), "Don't enough coins", Toast.LENGTH_SHORT)
//                    .show()
//            } else if (gameManager.help >= 0) {
//                gameManager.help -= 1
//                help()
//            }
//        }

        binding.btnHelp.setOnClickListener {
            if (gameManager.help >= 1) {
                binding.btnHelp.setImageResource(R.drawable.ic_help)
            } else if (gameManager.help == 0) {
                binding.btnHelp.setImageResource(R.drawable.ic_coins)
            }

            if (gameManager.help <= 0) {
                if (gameManager.coins >= 6) {
                    gameManager.coins -= 6
                    binding.coin.text = gameManager.coins.toString()
                    shared.setHelper(gameManager.help)
                    shared.setCoin(gameManager.coins)
                    shared.setChance(gameManager.help)
                    help()
                } else {
                    Toast.makeText(requireContext(), "Don't enough coins", Toast.LENGTH_SHORT)
                        .show()
                }
            } else if (gameManager.help >= 0) {
                gameManager.help -= 1
                help()
                binding.coin.text = gameManager.coins.toString()
                binding.chance.text = gameManager.help.toString()
                shared.setChance((gameManager.help))
                if (gameManager.help == 0) {
                    binding.chance.visibility = View.GONE
                } else {
                    binding.chance.visibility = View.VISIBLE
                }
            }
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

    private fun loadViews() {
        imagesList = ArrayList()
        wordList = ArrayList()
        lettersList = ArrayList()

        for (i in 0 until binding.wordLayout.childCount) {
            wordList.add(binding.wordLayout.getChildAt(i) as AppCompatButton)
            wordList[i].setOnClickListener {
                wordBtnClick(it as Button)
            }
        }

        for (i in 0 until binding.letterLayout.childCount) {
            lettersList.add(binding.letterLayout.getChildAt(i) as AppCompatButton)
            lettersList[i].setOnClickListener {
                string = ""
                letterBtnClick(it as Button)
                check()
            }
        }
    }

    private fun clear() {
        string = ""
        for (i in 0 until gameManager.getWordSize()) {
            wordList[i].text = ""
        }
        for (i in 0 until lettersList.size) {
            lettersList[i].visible()
            lettersList[i].text = "${gameManager.getLetters()[i]}"
        }
    }

    private fun letterBtnClick(button: Button) {
        if (button.isVisible && wordList[gameManager.getWordSize() - 1].text.isEmpty()) {
            button.invisible()
            val word = button.text.toString()
            for (i in 0 until wordList.size) {
                if (wordList[i].text.isEmpty()) {
                    wordList[i].text = word
                    break
                }
            }
        }
    }

    private fun wordBtnClick(it: Button) {
        if (it.text.isNotEmpty()) {
            val word = it.text.toString()
            it.text = ""
            for (i in 0 until lettersList.size) {
                if (lettersList[i].isInvisible()
                    && lettersList[i].text.toString().lowercase() == word.lowercase()
                ) {
                    lettersList[i].visible()
                    break
                }
            }
        }
    }

    private fun loadDataToView() {
        if (gameManager.level < question.getQuestions().size) {

            shared.setLevel(gameManager.level)
            shared.setCoin(gameManager.coins)
            shared.setHelper(gameManager.help)
            shared.setChance(gameManager.help)
            if (gameManager.help == 0) {
                binding.chance.visibility = View.GONE
            } else {
                binding.chance.visibility = View.VISIBLE
            }

            binding.coin.text = gameManager.coins.toString()
            binding.level.text = gameManager.level.toString()
            binding.chance.text = shared.getChance().toString()

            binding.image.setImageResource(question.getQuestions()[gameManager.level].image)

            if (gameManager.help >= 1) binding.btnHelp.setImageResource(R.drawable.ic_help)
            else if (gameManager.help == 0) binding.btnHelp.setImageResource(R.drawable.ic_coins)

            string = ""
            for (i in 0 until wordList.size) {
                if (gameManager.getWordSize() > i) {
                    wordList[i].visible()
                    wordList[i].text = ""
                } else wordList[i].gone()
            }

            if (gameManager.level % 9 == 0) gameManager.help += 5

            for (i in 0 until lettersList.size) {
                lettersList[i].visible()
                lettersList[i].text = "${gameManager.getLetters()[i]}"
            }
        } else {
            Toast.makeText(
                requireContext(),
                "Congratulated you passed this game",
                Toast.LENGTH_SHORT
            ).show()
            Extensions.controller?.startMainFragment(MenuFragment())
        }
    }

//    private fun help() {
//        var n = 0
//        var s = false
//        string = ""
//        for (i in 0 until wordList.size) {
//            if (gameManager.getWordSize() > i) {
//                if (wordList[i].text.isEmpty()) {
//                    n = i
//                    wordList[i].text = gameManager.getWord()[i].toString()
//                    s = true
//                    break
//                }
//            }
//            if (s) break
//
//        }
//
//        for (i in 0 until lettersList.size) {
//            if (wordList[n].text.toString() == lettersList[i].text.toString()) {
//                lettersList[i].invisible()
//                break
//            }
//        }
//        check()
//    }

    private fun help() {
        var n = 0
        var s = false
        string = ""
        for (i in 0 until wordList.size) {
            if (gameManager.getWordSize() > i) {
                if (wordList[i].text.isEmpty()) {
                    n = i
                    wordList[i].text = gameManager.getWord()[i].toString()
                    s = true
                    break
                }
            }
            if (s) {
                break
            }
        }

        for (i in 0 until lettersList.size) {
            if (wordList[n].text.toString() == lettersList[i].text.toString()) {
                lettersList[i].invisible()
                break
            }
        }
        check()
    }

//    fun check() {
//        for (j in 0 until wordList.size) {
//            if (gameManager.getWordSize() > j) string += wordList[j].text
//        }
//        if (string == gameManager.getWord()) {
//            Log.d("aaa", "loadViews: $string")
//            winDialogShow()
//            gameManager.coins += 4
//            gameManager.level += 1
//            loadDataToView()
//            binding.coin.text = gameManager.coins.toString()
//            binding.level.text = gameManager.level.toString()
//        } else if (string.length == gameManager.getWordSize() && string != gameManager.getWord()) {
//            errorDialogShow()
//            loadDataToView()
//        }
//    }

    private fun check() {
        for (j in 0 until wordList.size) {
            if (gameManager.getWordSize() > j) {
                string += wordList[j].text
            }
        }
        if (string == gameManager.getWord()) {
            Log.d("aaa", "loadViews: $string")
            winDialogShow()
            gameManager.coins += 2
            gameManager.level += 1
            loadDataToView()
            binding.coin.text = gameManager.coins.toString()
            binding.level.text = gameManager.level.toString()
        } else if (string.length == gameManager.getWordSize() && string != gameManager.getWord()) {
            errorDialogShow()
        }
    }

    private fun winDialogShow() {
        val builder: AestheticDialog.Builder =
            AestheticDialog.Builder(
                requireActivity(),
                DialogStyle.EMOTION,
                DialogType.SUCCESS
            )
        builder.setTitle("CORRECT")
        builder.setMessage("ANSWER : ${gameManager.getWord()}")
        builder.setAnimation(DialogAnimation.IN_OUT)
        builder.show()
    }

    private fun errorDialogShow() {
        val builder: AestheticDialog.Builder =
            AestheticDialog.Builder(
                requireActivity(),
                DialogStyle.EMOTION,
                DialogType.ERROR
            )
        builder.setTitle("INCORRECT")
        builder.setMessage(string.uppercase())
        builder.setAnimation(DialogAnimation.IN_OUT)
        builder.show()
    }

    override fun onDestroy() {
        shared.setCoin(gameManager.coins)
        shared.setLevel(gameManager.level)
        shared.setHelper(gameManager.help)

        super.onDestroy()
    }

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