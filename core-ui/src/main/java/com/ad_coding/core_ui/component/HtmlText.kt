package com.ad_coding.core_ui.component

import android.text.TextUtils
import android.widget.TextView
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

@Composable
fun HtmlText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    opacity: Float = 1.0f
) {
    val textColor = MaterialTheme.colorScheme.onBackground

    AndroidView(
        factory = { context -> TextView(context) },
        update = {
            it.setTextColor(textColor.hashCode())
            it.ellipsize = TextUtils.TruncateAt.END
            it.maxLines = maxLines
            it.text = HtmlCompat.fromHtml(
                text,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            it.alpha = opacity
        },
        modifier = modifier
    )
}