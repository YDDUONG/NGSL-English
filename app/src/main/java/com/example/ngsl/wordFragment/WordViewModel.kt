package com.example.ngsl.wordFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.ngsl.room.Word
import com.example.ngsl.room.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {
    //声明repository
    val allWord: LiveData<List<Word>>
    init {
        //word仓库
        val wordRepository = WordRepository.getInstance(application.applicationContext)
        allWord = wordRepository.allWord
    }
}
