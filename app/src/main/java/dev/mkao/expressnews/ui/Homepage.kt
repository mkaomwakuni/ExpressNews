package dev.mkao.expressnews.di

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dev.mkao.expressnews.R

@Composable
fun Homepage() {
		Column(modifier = Modifier
			.fillMaxSize()
			.wrapContentSize(Alignment.Center)) {
			Text(
				text = stringResource(id = R.string.Home),
				fontWeight = FontWeight.Bold,
				fontSize = 15.sp,
				color = Color.DarkGray,
				textAlign = TextAlign.Center,
				modifier = Modifier.align(Alignment.CenterHorizontally)
			)
		}
	}