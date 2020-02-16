package com.example.ngsl.wordFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.ngsl.room.Word
import com.example.ngsl.room.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {
    //声明repository
    private val wordRepository = WordRepository.getInstance(application.applicationContext)
    private val _allWord: LiveData<List<Word>> by lazy {
        wordRepository.allWord
    }
    val allWord: LiveData<List<Word>> get() = _allWord

    //查询单词
    fun searchWords(pattern: String): LiveData<List<Word>> {
        val key = "%${pattern}%"
//        Log.d("myLogPattern", key)
//        val data = wordRepository.searchWords(key)
//        Log.d("myLogData", data.value?.size.toString())
        return wordRepository.searchWords(key)
    }
}
