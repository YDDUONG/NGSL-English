package com.example.ngsl.room

import android.content.Context

//数据库相关的操作都应该放在这
class WordRepository private constructor(context: Context) {
    private val wordDao: WordDao by lazy {
        AppDatabase.getInstance(context).wordDao()
    }

//    init {
//        val appDatabase = AppDatabase.getInstance(context)
//        wordDao = appDatabase.wordDao()
//    }

    //获取所有数据
    val allWord = wordDao.selectAllValueFromWord()

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