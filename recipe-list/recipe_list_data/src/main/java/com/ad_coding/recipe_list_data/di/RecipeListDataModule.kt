package com.ad_coding.recipe_list_data.di

import android.app.Application
import com.ad_coding.core.data.network.SpoonacularApi
import com.ad_coding.recipe_list_data.repository.RecipeRepositoryImpl
import com.ad_coding.recipe_list_domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeListDataModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(api: SpoonacularApi): RecipeRepository =
        RecipeRepositoryImpl(api = api)
}