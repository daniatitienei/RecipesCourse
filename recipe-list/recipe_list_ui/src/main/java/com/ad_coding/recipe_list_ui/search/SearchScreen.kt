@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)

package com.ad_coding.recipe_list_ui.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ad_coding.core_ui.component.LoadingScreen
import com.ad_coding.recipe_list_ui.component.RecipeItem
import com.ad_coding.recipe_list_ui.component.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(SearchEvent.NavigateBack)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        AnimatedContent(targetState = state.isLoading) { isLoading ->
            if (isLoading) {
                LoadingScreen()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        top = padding.calculateTopPadding() + 15.dp,
                        bottom = padding.calculateBottomPadding() + 15.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        SearchBar(
                            value = state.query,
                            onValueChange = {
                                onEvent(SearchEvent.QueryValueChange(it))
                            },
                            onSearch = {
                                onEvent(SearchEvent.Search)
                            },
                            onClearText = {
                                onEvent(SearchEvent.ClearQuery)
                            },
                            placeholder = "Search",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    items(state.recipeList) { recipe ->
                        RecipeItem(
                            recipe = recipe,
                            onClick = {
                                onEvent(SearchEvent.RecipeClick(recipe))
                            }
                        )
                    }
                }
            }
        }
    }
}