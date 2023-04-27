@file:OptIn(ExperimentalMaterial3Api::class)

package com.ad_coding.recipe_information_ui.component

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ad_coding.core.data.network.SpoonacularApi
import com.ad_coding.recipe_information_domain.model.RecipeInformation
import com.ad_coding.recipe_information_ui.RecipeInformationEvent
import com.ad_coding.recipe_information_ui.RecipeInformationState

@Composable
fun IngredientList(
    onEvent: (RecipeInformationEvent) -> Unit,
    state: RecipeInformationState,
    recipe: RecipeInformation?,
    context: Context
) {
    Column(
        modifier = Modifier
            .clickable {
                onEvent(RecipeInformationEvent.ToggleIngredients)
            }
            .padding(vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.titleMedium
            )

            ExposedDropdownMenuDefaults.TrailingIcon(expanded = state.areIngredientsVisible)
        }
        AnimatedVisibility(
            visible = state.areIngredientsVisible,
            enter = fadeIn(tween(200)) + slideInVertically(tween(300)),
            exit = fadeOut(tween(200)) + slideOutVertically(tween(300))
        ) {
            Column {
                recipe?.extendedIngredients?.map { ingredient ->
                    ListItem(
                        headlineText = {
                            Text(text = ingredient.originalName)
                        },
                        supportingText = {
                            Text(text = "${ingredient.amount} ${ingredient.unit}")
                        },
                        leadingContent = {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(
                                        SpoonacularApi.ingredientImageUrl.replace(
                                            "{name}",
                                            ingredient.image
                                        )
                                    )
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Ingredient image",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(10.dp)
                                    )
                                    .size(56.dp)
                            )
                        }
                    )
                }
            }
        }
    }
}