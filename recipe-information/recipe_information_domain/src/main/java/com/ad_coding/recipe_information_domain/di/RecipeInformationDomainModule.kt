package com.ad_coding.recipe_information_domain.di

import com.ad_coding.recipe_information_domain.repository.RecipeInformationRepository
import com.ad_coding.recipe_information_domain.use_case.GetRecipeInformation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeInformationDomainModule {

    @Provides
    @Singleton
    fun provideGetRecipeInformation(
        repository: RecipeInformationRepository
    ): GetRecipeInformation =
        GetRecipeInformation(repository = repository)
}