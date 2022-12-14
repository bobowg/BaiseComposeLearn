package com.example.baisecomposelearn.screens.components.exoplayer

import android.content.Context
import androidx.media3.database.ExoDatabaseProvider
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache

/**
 * 缓存基本设置，exo内部会提供一个命名 exoplayer_internal.db 的数据库作为缓存
 * */
object CacheHolder {
    private var cache: SimpleCache? = null
    private val lock = Object()

    fun get(context: Context): SimpleCache {
        synchronized(lock) {
            if (cache == null) {
                val cacheSize = 20L * 1024 * 1024
                val exoDatabaseProvider = ExoDatabaseProvider(context)

                cache = SimpleCache(
                    // 缓存文件地址
                    context.cacheDir,
                    // 释放上次的缓存数据
                    LeastRecentlyUsedCacheEvictor(cacheSize),
                    // 提供数据库
                    exoDatabaseProvider
                )
            }
        }
        return cache!!
    }
}