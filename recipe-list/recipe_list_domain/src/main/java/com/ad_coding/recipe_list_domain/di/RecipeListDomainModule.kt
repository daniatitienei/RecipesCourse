package com.ad_coding.recipe_list_domain.di

import com.ad_coding.recipe_list_domain.repository.RecipeRepository
import com.ad_coding.recipe_list_domain.use_case.GetRecipeList
import com.ad_coding.recipe_list_domain.use_case.RecipeListUseCases
import com.ad_coding.recipe_list_domain.use_case.SearchRecipe
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeListDomainModule {

    @Provides
    @Singleton
    fun provideRecipeListUseCases(
        repository: RecipeRepository
    ): RecipeListUseCases = RecipeListUseCases(
        getRecipeList = GetRecipeList(repository),
        searchRecipe = SearchRecipe(repository)
    )
}