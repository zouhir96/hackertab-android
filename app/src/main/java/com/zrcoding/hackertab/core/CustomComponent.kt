package com.zrcoding.hackertab.core

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.theme.HackertabTheme
import com.zrcoding.hackertab.theme.TextLink
import com.zrcoding.hackertab.theme.dimension

data class ChipData(
    val id: String,
    val name: String,
    @DrawableRes val image: Int? = null,
    val selected: Boolean = false
)

@Composable
fun Chip(
    chipData: ChipData,
    isSelected: Boolean = false,
    onClick: (ChipData) -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(MaterialTheme.dimension.big),
        color = if (isSelected) TextLink else MaterialTheme.colors.secondary
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick(chipData) }
                .padding(horizontal = MaterialTheme.dimension.large),
            verticalAlignment = Alignment.CenterVertically
        ) {
            chipData.image?.let {
                Icon(
                    modifier = Modifier
                        .padding(end = MaterialTheme.dimension.medium)
                        .size(MaterialTheme.dimension.big),
                    painter = painterResource(id = it),
                    contentDescription = null,
                    tint = MaterialTheme.colors.surface
                )
            }

            Text(
                modifier = Modifier.padding(vertical = MaterialTheme.dimension.medium),
                text = chipData.name,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    chips: List<ChipData>,
    onChipClicked: (ChipData) -> Unit,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimension.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimension.medium),
    ) {
        chips.forEach { chip ->
            Chip(
                chipData = chip,
                isSelected = chip.selected,
                onClick = onChipClicked
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ChipGroupPreview() {
    HackertabTheme {
        ChipGroup(
            chips = listOf(
                ChipData(id = "1", name = "chip 1"),
                ChipData(id = "2", name = "chip 2"),
                ChipData(id = "3", name = "chip 3"),
                ChipData(id = "2", name = "Reddit", R.drawable.ic_reddit),
                ChipData(id = "4", name = "chip 4"),
                ChipData(id = "5", name = "chip 4", selected = true),
                ChipData(id = "6", name = "chip 4"),
                ChipData(id = "7", name = "chip 4"),
                ChipData(id = "8", name = "chip 4"),
                ChipData(id = "1", name = "Github repositories", image = R.drawable.ic_github),
                ChipData(id = "9", name = "chip 4"),
                ChipData(id = "42", name = "chip 4"),
                ChipData(
                    id = "3",
                    name = "FreeCodeCamo",
                    R.drawable.ic_freecodecamp,
                    selected = true
                ),
            ),
        ) {}
    }
}


