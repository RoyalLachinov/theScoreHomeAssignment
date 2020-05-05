package com.thescore.android.api.network

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.thescore.android.constants.CoreConstants
import com.thescore.android.constants.UserManager
import com.thescore.android.di.TheScoreSingletonAnnotation
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

@TheScoreSingletonAnnotation
class ApiClient @Inject constructor(private val userManager: UserManager) {

    fun getRetrofitInstance(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.cache(
            Cache(
                userManager.sharedPreferenceStorage.context.getDir(
                    CoreConstants.THE_SCORE_CACHE,
                    Context.MODE_PRIVATE
                ), CoreConstants.MAX_DISK_CACHE_SIZE
            )
        )
        okHttpClient.addInterceptor(interceptor)
        okHttpClient.connectTimeout(2, TimeUnit.MINUTES)
        okHttpClient.readTimeout(2, TimeUnit.MINUTES)
        okHttpClient.writeTimeout(2, TimeUnit.MINUTES)
        okHttpClient.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request =
                chain.request().newBuilder().addHeader("Accept", "application/json").build()
            chain.proceed(request)
        })

        return Retrofit.Builder()
            .baseUrl(userManager.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient.build())
            .build()
    }
}