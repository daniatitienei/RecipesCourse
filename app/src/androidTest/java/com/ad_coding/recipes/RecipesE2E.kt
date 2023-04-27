package com.ad_coding.recipes

import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ad_coding.recipe_information_domain.repository.RecipeInformationRepository
import com.ad_coding.recipe_information_domain.use_case.GetRecipeInformation
import com.ad_coding.recipe_information_ui.RecipeInformationEvent
import com.ad_coding.recipe_information_ui.RecipeInformationScreen
import com.ad_coding.recipe_information_ui.RecipeInformationViewModel
import com.ad_coding.recipe_list_domain.repository.RecipeRepository
import com.ad_coding.recipe_list_domain.use_case.GetRecipeList
import com.ad_coding.recipe_list_domain.use_case.RecipeListUseCases
import com.ad_coding.recipe_list_domain.use_case.SearchRecipe
import com.ad_coding.recipe_list_ui.list.RecipeListEvent
import com.ad_coding.recipe_list_ui.list.RecipeListScreen
import com.ad_coding.recipe_list_ui.list.RecipeListViewModel
import com.ad_coding.recipe_list_ui.search.SearchEvent
import com.ad_coding.recipe_list_ui.search.SearchScreen
import com.ad_coding.recipe_list_ui.search.SearchViewModel
import com.ad_coding.recipes.navigation.Route
import com.ad_coding.recipes.repository.RecipeInformationRepositoryFake
import com.ad_coding.recipes.repository.RecipeRepositoryFake
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class RecipesE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var recipeRepositoryFake: RecipeRepository
    private lateinit var recipeInformationFake: RecipeInformationRepository

    private lateinit var recipeListUseCases: RecipeListUseCases
    private lateinit var getRecipeInformationUseCase: GetRecipeInformation

    private lateinit var recipeListViewModel: RecipeListViewModel
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var recipeInformationViewModel: RecipeInformationViewModel

    private lateinit var navController: NavHostController

    @Before
    fun setUp() {
        recipeRepositoryFake = RecipeRepositoryFake()
        recipeInformationFake = RecipeInformationRepositoryFake()

        recipeListUseCases = RecipeListUseCases(
            getRecipeList = GetRecipeList(recipeRepositoryFake),
            searchRecipe = SearchRecipe(recipeRepositoryFake)
        )
        getRecipeInformationUseCase = GetRecipeInformation(recipeInformationFake)

        recipeListViewModel = RecipeListViewModel(recipeListUseCases)
        searchViewModel = SearchViewModel(recipeListUseCases)
        recipeInformationViewModel = RecipeInformationViewModel(
            savedStateHandle = SavedStateHandle(),
            getRecipeInformation = getRecipeInformationUseCase
        )

        composeRule.activity.setContent {
            navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Route.recipeList
            ) {
                composable(route = Route.recipeList) {
                    val state by recipeListViewModel.state.collectAsStateWithLifecycle()

                    RecipeListScreen(
                        state = state,
                        onEvent = { event ->
                            when (event) {
                                is RecipeListEvent.RecipeClick -> navController.navigate(
                                    Route.recipeInformation.replace(
                                        "{id}",
                                        event.recipe.id.toString()
                                    )
                                )

                                RecipeListEvent.SearchClick -> {
                                    navController.navigate(Route.search)
                                }

                                else -> recipeListViewModel.onEvent(event)
                            }
                        }
                    )
                }

                composable(route = Route.search) {
                    val state by searchViewModel.state.collectAsStateWithLifecycle()

                    SearchScreen(
                        state = state,
                        onEvent = { event ->
                            when (event) {
                                SearchEvent.NavigateBack -> navController.popBackStack()

                                is SearchEvent.RecipeClick ->
                                    navController.navigate(
                                        Route.recipeInformation.replace(
                                            "{id}",
                                            event.recipe.id.toString()
                                        )
                                    )

                                else -> searchViewModel.onEvent(event)
                            }
                        }
                    )
                }

                composable(route = Route.recipeInformation) {
                    val state by recipeInformationViewModel.state.collectAsStateWithLifecycle()

                    RecipeInformationScreen(
                        state = state,
                        onEvent = { event ->
                            when (event) {
                                RecipeInformationEvent.NavigateBack -> {
                                    navController.popBackStack()
                                }

                                else -> recipeInformationViewModel.onEvent(event)
                            }
                        }
                    )
                }
            }
        }
    }

    @Test
    fun clickOnSearchBar_searchPizza_recipeProperlyShowsUp() {
        composeRule.onNodeWithContentDescription("Search").performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.search)
        ).isTrue()

        val searchBar = composeRule.onNodeWithContentDescription("Search")
        searchBar.performClick()
        searchBar.performTextInput("Pizza")
        searchBar.performImeAction()

        composeRule
            .onAllNodesWithTag("recipe_item")
            .onFirst()
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.recipeInformation)
        ).isTrue()
    }
}