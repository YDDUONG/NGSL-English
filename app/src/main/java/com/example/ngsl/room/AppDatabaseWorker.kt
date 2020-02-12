package com.example.ngsl.room

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.ngsl.WORD_JSON_FILE_NAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope

//新建数据库时，会执行以下任务：将json文件导入数据库
class AppDatabaseWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(WORD_JSON_FILE_NAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val wordType = object : TypeToken<List<Word>>() {}.type
                    val wordList: List<Word> = Gson().fromJson(jsonReader, wordType)

                    val wordRepository = WordRepository.getInstance(applicationContext)
                    wordRepository.insertAll(wordList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = AppDatabaseWorker::class.java.simpleName
    }
}