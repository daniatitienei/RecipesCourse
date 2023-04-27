@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)

package com.ad_coding.recipe_list_ui.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.ad_coding.core_ui.component.LoadingScreen
import com.ad_coding.core_ui.theme.RecipesTheme
import com.ad_coding.recipe_list_ui.component.RecipeItem
import com.ad_coding.recipe_list_ui.component.SearchBar

@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onEvent: (RecipeListEvent) -> Unit
) {
    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = state.isLoading,
            onRefresh = {
                onEvent(RecipeListEvent.Refresh)
            }
        )

    Scaffold { padding ->
        AnimatedContent(targetState = state.isLoading) { isLoading ->
            if (isLoading) {
                LoadingScreen()
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(
                            state = pullRefreshState
                        )
                ) {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            top = padding.calculateTopPadding() + 15.dp,
                            bottom = padding.calculateBottomPadding() + 15.dp,
                            start = 20.dp,
                            end = 20.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item {
                            SearchBar(
                                value = "",
                                onValueChange = {},
                                placeholder = "Search",
                                readOnly = true,
                                enabled = false,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onEvent(RecipeListEvent.SearchClick)
                                    }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        items(state.recipeList) { recipe ->
                            RecipeItem(
                                recipe = recipe,
                                onClick = {
                                    onEvent(RecipeListEvent.RecipeClick(recipe = recipe))
                                }
                            )
                        }
                    }
                    PullRefreshIndicator(
                        refreshing = state.isLoading,
                        state = pullRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }
    }
}

@Preview(wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE)
@Composable
private fun RecipeListPreview() {
    RecipesTheme {
        RecipeListScreen(
            state = RecipeListState(),
            onEvent = {}
        )
    }
}