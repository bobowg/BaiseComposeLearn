package com.example.baisecomposelearn.model

import android.content.Context
import java.io.File

class CleanCache {
    fun cleanCache(context: Context){
        val cacheDir = context.cacheDir
        val appDir = File(context.filesDir.parent)
        if (cacheDir.isDirectory){
            cacheDir.list()?.forEach {
                File(cacheDir,it).delete()
            }
        }
        if (appDir.isDirectory){
            appDir.list()?.forEach {
                if (it !="lib"){
                    File(appDir,it).delete()
                }
            }
        }
    }

    fun clearData(context: Context) {
        val filesDir = context.filesDir
        val cacheDir = context.cacheDir
        val sharedPrefs = File(context.applicationContext.filesDir.parentFile, "shared_prefs")
        val databases = File(context.applicationContext.filesDir.parentFile, "databases")
        if (filesDir.isDirectory) {
            filesDir.list()?.forEach {
                File(filesDir, it).delete()
            }
        }
        if (cacheDir.isDirectory) {
            cacheDir.list()?.forEach {
                File(cacheDir, it).delete()
            }
        }
        if (sharedPrefs.isDirectory) {
            sharedPrefs.list()?.forEach {
                File(sharedPrefs, it).delete()
            }
        }
        if (databases.isDirectory) {
            databases.list()?.forEach {
                File(databases, it).delete()
            }
        }
    }
}