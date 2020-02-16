package com.example.ngsl.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(word: List<Word>)

    //查询全部数据
    @Query("SELECT * FROM word_table")
    fun selectAllValueFromWord(): LiveData<List<Word>>

    //查询单词
    @Query("select * from word_table where english like :pattern")
    fun searchWords(pattern: String): LiveData<List<Word>>
}