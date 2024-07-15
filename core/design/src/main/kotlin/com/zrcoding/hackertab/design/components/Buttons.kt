package com.zrcoding.hackertab.design.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.zrcoding.hackertab.design.theme.dimension

@Composable
fun RoundedIconButton(
    modifier: Modifier = Modifier,
    size: Dp,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .padding(start = MaterialTheme.dimension.default)
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colors.background),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground
        )
    }
}