package com.ad_coding.recipe_information_ui.component

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ad_coding.recipe_information_domain.model.RecipeInformation
import com.ad_coding.recipe_information_ui.RecipeInformationEvent

@Composable
fun RecipeInformationHeader(
    context: Context,
    recipe: RecipeInformation?,
    onEvent: (RecipeInformationEvent) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight / 3)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(recipe?.image)
                .crossfade(true)
                .build(),
            contentDescription = "Food image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .fillMaxSize()
        )
        Box(
            modifier = Modifier.padding(20.dp)
        ) {
            FilledTonalIconButton(
                onClick = {
                    onEvent(RecipeInformationEvent.NavigateBack)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        }
    }
}