package com.example.ngsl.room

import android.content.Context
import androidx.lifecycle.LiveData

//数据库相关的操作都应该放在这
class WordRepository private constructor(context: Context) {
    private val wordDao: WordDao by lazy {
        AppDatabase.getInstance(context).wordDao()
    }

    //获取所有数据
    val allWord = wordDao.selectAllValueFromWord()

    //查询单词
    fun searchWords(pattern: String): LiveData<List<Word>> {
        return wordDao.searchWords(pattern)
    }

    //插入数据
    suspend fun insertAll(word: List<Word>) {
        wordDao.insertAll(word)
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: WordRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: WordRepository(context).also { instance = it }
            }
    }
}