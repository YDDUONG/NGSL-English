package com.example.ngsl.wordFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.ngsl.room.Word
import com.example.ngsl.room.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {
    //声明repository
    private val _allWord: LiveData<List<Word>> by lazy {
        WordRepository.getInstance(application.applicationContext).allWord
    }
    //    init {
//        //word仓库
//        val wordRepository = WordRepository.getInstance(application.applicationContext)
//        _allWord = wordRepository.allWord
//    }
    val allWord: LiveData<List<Word>> get() = _allWord
}
