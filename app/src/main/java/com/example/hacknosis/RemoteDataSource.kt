package com.example.hacknosis

import com.example.hacknosis.data.OpenTextApi
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val openTextApi: OpenTextApi
) {

    /*suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return openTextApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return openTextApi.searchRecipes(searchQuery)
    }*/

}