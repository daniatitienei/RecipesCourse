package com.ad_coding.recipe_information_data.di

import com.ad_coding.core.data.network.SpoonacularApi
import com.ad_coding.recipe_information_data.repository.RecipeInformationRepositoryImpl
import com.ad_coding.recipe_information_domain.repository.RecipeInformationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeInformationDataModule {

    @Provides
    @Singleton
    fun provideRecipeInformationRepository(
        api: SpoonacularApi
    ): RecipeInformationRepository =
        RecipeInformationRepositoryImpl(
            api = api
        )
}