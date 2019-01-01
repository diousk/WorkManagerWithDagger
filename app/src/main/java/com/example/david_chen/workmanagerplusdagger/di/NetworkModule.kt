package com.example.david_chen.workmanagerplusdagger.di

import android.content.Context
import com.example.chenweiming.mykktapplication.api.WikiApiService
import com.example.david_chen.workmanagerplusdagger.BuildConfig
import com.example.david_chen.workmanagerplusdagger.R
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by david.chen@soocii.me on 2018/11/15.
 */

@Module
class NetworkModule {

    @Named("image")
    @Provides
    @Singleton
    fun provideImageOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(5, 25, TimeUnit.SECONDS))
            .retryOnConnectionFailure(false)
            .protocols(listOf(Protocol.HTTP_1_1))
            .build()
    }

    @Named("api")
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // thread dispatcher
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 64
        dispatcher.maxRequestsPerHost = 16

        // logging interceptor
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.HEADERS
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
            // for debug complete raw response data
            //logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .connectTimeout(30, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .connectionPool(ConnectionPool(5, 50, TimeUnit.SECONDS))
            .addInterceptor(logging) // enable log
            .protocols(listOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(context: Context, @Named("api") okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        val awsAddress = context.getString(R.string.server_address)
        return Retrofit.Builder()
            .baseUrl(awsAddress)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): WikiApiService {
        return retrofit.create(WikiApiService::class.java)
    }
}