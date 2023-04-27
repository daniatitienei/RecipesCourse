package com.ad_coding.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ad_coding.core_ui.theme.RecipesTheme
import com.ad_coding.recipe_information_ui.RecipeInformationEvent
import com.ad_coding.recipe_information_ui.RecipeInformationScreen
import com.ad_coding.recipe_information_ui.RecipeInformationViewModel
import com.ad_coding.recipe_list_ui.list.RecipeListEvent
import com.ad_coding.recipe_list_ui.list.RecipeListScreen
import com.ad_coding.recipe_list_ui.list.RecipeListViewModel
import com.ad_coding.recipe_list_ui.search.SearchEvent
import com.ad_coding.recipe_list_ui.search.SearchScreen
import com.ad_coding.recipe_list_ui.search.SearchViewModel
import com.ad_coding.recipes.navigation.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesTheme {
                val navController = rememberNavController()
                
                NavHost(
                    navController = navController,
                    startDestination = Route.recipeList
                ) {
                    composable(route = Route.recipeList) {
                        val viewModel = hiltViewModel<RecipeListViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

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

                                    else -> viewModel.onEvent(event)
                                }
                            }
                        )
                    }

                    composable(route = Route.search) {
                        val viewModel = hiltViewModel<SearchViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

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

                                    else -> viewModel.onEvent(event)
                                }
                            }
                        )
                    }
                    
                    composable(route = Route.recipeInformation) {
                        val viewModel = hiltViewModel<RecipeInformationViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        RecipeInformationScreen(
                            state = state,
                            onEvent = { event ->
                                when (event) {
                                    RecipeInformationEvent.NavigateBack -> {
                                        navController.popBackStack()
                                    }

                                    else -> viewModel.onEvent(event)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}