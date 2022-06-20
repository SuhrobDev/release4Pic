package com.soul.mygame4pic.models

//@Entity(tableName = "questionData")
data class QuestionData(
//    @PrimaryKey(autoGenerate = true)
//    var id:Int = 0,
    var image: Int,
    var word: String,
    var letters: String
)