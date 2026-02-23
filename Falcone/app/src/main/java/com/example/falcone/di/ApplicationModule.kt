package com.example.falcone.di

import android.app.Application
import android.content.res.Resources
import com.example.falcone.repository.local.FalconDao
import com.example.falcone.repository.local.FalconDatabase
import com.example.falcone.repository.remote.FalconService
import com.example.falcone.repository.remote.FalconService.Companion.BASE_URL
import com.example.falcone.repository.remote.ResultCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object ApplicationModule {

    @ApplicationScope
    @Provides
    internal fun provideResource(applicationContext: Application): Resources = applicationContext.resources

    @ApplicationScope
    @Provides
    internal fun provideRetrofit(gson: Gson, httpClient: OkHttpClient, resultCallAdapterFactory: ResultCallAdapterFactory): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(resultCallAdapterFactory)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @ApplicationScope
    fun provideOkhttpClient(resources: Resources): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideFalconService(gson: Gson, httpClient: OkHttpClient, resultCallAdapterFactory: ResultCallAdapterFactory): FalconService {
        return provideRetrofit(gson, httpClient, resultCallAdapterFactory).create(FalconService::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideFalconDatabase(applicationContext: Application): FalconDatabase {
        return FalconDatabase.getDatabase(applicationContext)
    }

    @Provides
    @ApplicationScope
    fun provideFalconDao(falconDatabase: FalconDatabase): FalconDao {
        return falconDatabase.falconDao()
    }
}