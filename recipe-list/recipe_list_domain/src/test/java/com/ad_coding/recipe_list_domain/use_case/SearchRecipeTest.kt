package com.ad_coding.recipe_list_domain.use_case

import com.ad_coding.recipe_list_data.repository.RecipeRepositoryFake
import com.ad_coding.recipe_list_domain.repository.RecipeRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchRecipeTest {

    private lateinit var repository: RecipeRepository

    @Before
    fun setUp() {
        repository = RecipeRepositoryFake()
    }

    @Test
    fun execute() {
        runBlocking {
            val recipes = repository.searchRecipes("Pizza")

            recipes.forEach { recipe ->
                assertThat(recipe.title)
                    .contains("Pizza")
            }
        }
    }
}