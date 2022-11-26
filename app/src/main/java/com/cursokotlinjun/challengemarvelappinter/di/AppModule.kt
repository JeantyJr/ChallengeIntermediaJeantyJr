package com.cursokotlinjun.challengemarvelappinter.di

import com.cursokotlinjun.challengemarvelappinter.BuildConfig
import com.cursokotlinjun.challengemarvelappinter.data.data_source.CharacterDataSource
import com.cursokotlinjun.challengemarvelappinter.data.data_source.EventDataSource
import com.cursokotlinjun.challengemarvelappinter.data.service.EventAPI
import com.cursokotlinjun.challengemarvelappinter.data.service.MarvelAPI
import com.cursokotlinjun.challengemarvelappinter.domain.repository.CharacterRepositoryImpl
import com.cursokotlinjun.challengemarvelappinter.domain.repository.CharactersRepository
import com.cursokotlinjun.challengemarvelappinter.domain.repository.EventRepository
import com.cursokotlinjun.challengemarvelappinter.domain.repository.EventRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @ApiMarvel
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_API_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    fun provideCharactersApi(@ApiMarvel retrofit: Retrofit): MarvelAPI =
        retrofit.create(MarvelAPI::class.java)

    @Provides
    fun providesCharactersDataSource(marvelAPI: MarvelAPI): CharacterDataSource =
        CharacterDataSource(marvelAPI)

    @Provides
    fun provideEventsApi(@ApiMarvel retrofit: Retrofit): EventAPI =
        retrofit.create(EventAPI::class.java)

    @Provides
    fun provideEventsDataSource(eventAPI: EventAPI): EventDataSource =
        EventDataSource(eventAPI)
//    @Provides
//    fun provideEventRepository(eventDataSource: EventDataSource): EventRepositoryImpl =
//        EventRepositoryImpl(eventDataSource)
//
//    @Provides
//    fun provideCharacterRepository(characterDataSource: CharacterDataSource): CharacterRepositoryImpl =
//        CharacterRepositoryImpl(characterDataSource)
}