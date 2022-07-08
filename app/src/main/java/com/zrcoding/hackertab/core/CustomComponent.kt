package com.zrcoding.hackertab.core

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zrcoding.hackertab.R
import com.zrcoding.hackertab.ui.theme.TextLink

@Preview
@Composable
fun Chip(
    chipModel: ChipData = ChipData("Hello", R.drawable.ic_github),
    isSelected: Boolean = false,
    onSelectionChanged: (ChipData) -> Unit = {},
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) TextLink else MaterialTheme.colors.secondary
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectionChanged(chipModel)
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            chipModel.image?.let {
                Image(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(16.dp)
                        .width(16.dp),
                    painter = painterResource(id = it),
                    contentDescription = ""
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                text = chipModel.name,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun ChipGroup(
    chips: List<ChipData>,
    selectedChip: ChipData? = null,
    onSelectedChanged: (ChipData) -> Unit = {},
) {
    Surface {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = Color.Blue)
        ) {

            val chunked = chips.chunked(5)

            items((0 until 2).toList()) { index ->
                Row(
                    modifier = Modifier
                        .background(color = Color.Red)
                ) {
                    chunked[index].forEach { currentChip ->
                        Chip(
                            chipModel = currentChip,
                            isSelected = selectedChip?.id == currentChip.id,
                            onSelectionChanged = {
                                onSelectedChanged(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

data class ChipData(
    val name: String,
    @DrawableRes val image: Int? = null,
    val id: String = name
)

