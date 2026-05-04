package com.example.recolectores.core.designsystem.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recolectores.core.designsystem.layout.LocalBanSpacing
import com.example.recolectores.core.designsystem.theme.BanBorder
import com.example.recolectores.core.designsystem.theme.BanSurface


@Composable
fun BanCard(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(LocalBanSpacing.large),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(LocalBanSpacing.small),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = BanSurface
        ),
        border = BorderStroke(
            width = 1.1.dp,
            color = BanBorder
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = verticalArrangement,
            content = content
        )
    }
}