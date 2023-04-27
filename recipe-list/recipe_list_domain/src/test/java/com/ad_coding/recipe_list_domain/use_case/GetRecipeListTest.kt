package com.ad_coding.recipe_list_domain.use_case

import com.ad_coding.recipe_list_data.repository.RecipeRepositoryFake
import com.ad_coding.recipe_list_domain.model.Recipe
import com.ad_coding.recipe_list_domain.repository.RecipeRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class GetRecipeListTest {

    private lateinit var repository: RecipeRepository

    @Before
    fun setUp() {
        repository = RecipeRepositoryFake()
    }

    @Test
    fun execute() {
        val recipes = repository.getRandomRecipes()

        assertThat(recipes)
            .isNotEqualTo(emptyList<Recipe>())
    }
}