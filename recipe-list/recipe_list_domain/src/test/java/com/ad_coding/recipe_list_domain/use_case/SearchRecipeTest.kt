package com.ad_coding.recipe_list_domain.use_case

import com.ad_coding.recipe_list_data.repository.RecipeRepositoryFake
import com.ad_coding.recipe_list_domain.repository.RecipeRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchRecipeTest {

    private lateinit var searchRecipe: SearchRecipe
    private lateinit var repositoryFake: RecipeRepositoryFake

    @Before
    fun setUp() {
        repositoryFake = RecipeRepositoryFake()
        searchRecipe = SearchRecipe(repositoryFake)
    }

    @Test
    fun `Query returns the expected result`() {
        runBlocking {
            val recipes = searchRecipe.execute("Pizza")

            recipes.forEach { recipe ->
                assertThat(recipe.title)
                    .contains("Pizza")
            }
        }
    }
}