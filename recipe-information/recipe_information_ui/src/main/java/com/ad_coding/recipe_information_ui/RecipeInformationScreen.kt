@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)

package com.ad_coding.recipe_information_ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ad_coding.core_ui.component.HtmlText
import com.ad_coding.core_ui.component.LoadingScreen
import com.ad_coding.recipe_information_ui.component.IngredientList
import com.ad_coding.recipe_information_ui.component.RecipeInformationHeader

@Composable
fun RecipeInformationScreen(
    state: RecipeInformationState,
    onEvent: (RecipeInformationEvent) -> Unit
) {
    val context = LocalContext.current
    val recipe = state.recipe

    AnimatedContent(targetState = state.isLoading) { isLoading ->
        if (isLoading) {
            LoadingScreen()
        } else {
            Scaffold { padding ->
                LazyColumn(
                    contentPadding = PaddingValues(
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding() + 15.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        RecipeInformationHeader(
                            context = context,
                            recipe = recipe,
                            onEvent = onEvent
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = recipe?.title.orEmpty(),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold
                            )

                            HtmlText(
                                text = recipe?.summary.orEmpty()
                            )

                            IngredientList(
                                onEvent = onEvent,
                                state = state,
                                recipe = recipe,
                                context = context
                            )

                            Text(
                                text = "Instructions",
                                style = MaterialTheme.typography.titleMedium
                            )

                            HtmlText(
                                text = recipe?.instructions.orEmpty()
                            )
                        }
                    }
                }
            }
        }
    }
}