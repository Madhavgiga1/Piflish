package com.example.hacknosis.di

import com.example.hacknosis.data.OpenTextApi
import com.example.hacknosis.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)// @InstallIn annotation specifies the component that the module is installed in. In this case, the module is installed in a singleton componen
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providesRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }
    /*@Provides annotation, which indicates that it is used to provide a dependency for the app. The method is also annotated
    with the @Singleton annotation, which indicates that the dependency is a singleton instance. This means that the same
    instance of the FoodRecipesApi interface will be provided to all components of the app that depend on it.*/
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): OpenTextApi {
        return retrofit.create(OpenTextApi::class.java)
    }
    /*the method creates and returns an instance of the FoodRecipesApi interface by calling the create() method on the Retrofit instance and passing the
     FoodRecipesApi interface as a parameter. The create() method creates an implementation of the FoodRecipesApi interface that is backed by a Retrofit instance, and it can be used to
     call the methods defined in the FoodRecipesApi interface to retrieve data from the REST API.*/
}