package com.ad_coding.core.data.network

import com.ad_coding.core.data.network.dto.RecipeInformationDto
import com.ad_coding.core.data.network.dto.RecipeListDto
import com.ad_coding.core.data.network.dto.SearchRecipeListDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularApi {

    @Headers("x-api-key: ${Secrets.apiKey}")
    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int
    ): RecipeListDto

    @Headers("x-api-key: ${Secrets.apiKey}")
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String
    ): SearchRecipeListDto

    @Headers("x-api-key: ${Secrets.apiKey}")
    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: String
    ): RecipeInformationDto?

    companion object {
        const val baseUrl = "https://api.spoonacular.com/"
        const val ingredientImageUrl = "https://spoonacular.com/cdn/ingredients_100x100/{name}"
    }
}