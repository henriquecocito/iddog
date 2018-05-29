package me.henriquecocito.iddog.base.data

import me.henriquecocito.iddog.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


abstract class BaseRepository {

    protected fun <T> getAPI(javaClass: Class<T>, token: String?): T {

        val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .addInterceptor { chain ->
                    token?.let {
                        chain.request()?.newBuilder()?.addHeader("Authorization", token)?.build()?.let {
                            chain.proceed(it)
                        }
                    }
                }
                .build()

        var repository = Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()


        return repository.create(javaClass)
    }
}