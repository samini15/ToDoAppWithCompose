package com.example.todoappwithcompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.todoappwithcompose.R
import com.example.todoappwithcompose.ui.theme.MediumGray

@Composable
fun EmptyContent(topPadding: Dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background).padding(top = topPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.ic_very_dissatisfied),
            contentDescription = stringResource(id = R.string.dissatisfied_icon_content_description),
            tint = MediumGray
        )
        Text(
            text = stringResource(id = R.string.empty_tasks_content),
            color = MediumGray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
private fun EmptyContentPreview() {
    EmptyContent(topPadding = 10.dp)
}