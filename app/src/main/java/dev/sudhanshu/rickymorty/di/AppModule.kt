package dev.sudhanshu.rickymorty.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.sudhanshu.rickymorty.data.remote.RickAndMortyApi
import dev.sudhanshu.rickymorty.data.repository.CharacterRepositoryImpl
import dev.sudhanshu.rickymorty.domain.repository.CharacterRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideRickAndMortyApi(retrofit: Retrofit): RickAndMortyApi =
        retrofit.create(RickAndMortyApi::class.java)

    @Provides
    @Singleton
    fun provideCharacterRepository(api: RickAndMortyApi): CharacterRepositoryImpl =
        CharacterRepositoryImpl(api)
}
