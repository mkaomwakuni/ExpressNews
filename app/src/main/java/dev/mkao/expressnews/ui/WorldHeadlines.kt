package dev.mkao.expressnews.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.mkao.expressnews.R
import dev.mkao.expressnews.model.News
import dev.mkao.expressnews.model.generateDummyNewsList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WorldHeadlines(navController: NavController) {
	val sourceList = listOf("All", "nTechnology", "Sports", "Politics", "Finance", "War", "World")
	var selectedTab by remember { mutableStateOf(0) }
	
	Scaffold(
		backgroundColor = colorResource(id = R.color.white),
		topBar = {
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 10.dp)
					.statusBarsPadding()
			) {
				IconButton(onClick = { /*TODO*/ }) {
					Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
				}
				Spacer(modifier = Modifier.height(20.dp))
				
				Column(
					modifier = Modifier
						.padding(start = 16.dp)
						.fillMaxWidth(),
					verticalArrangement = Arrangement.SpaceBetween,
					horizontalAlignment = Alignment.Start
				) {
					Text(
						text = "Discover",
						color = Color.Black,
						fontSize = 28.sp,
						fontWeight = FontWeight.Bold,
					)
					Spacer(modifier = Modifier.height(5.dp))
					Text(
						text = "News from all around the world",
						color = Color.LightGray,
						fontSize = 10.sp,
						fontWeight = FontWeight.Bold,
					)
				}
			}
		},
		content = {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(horizontal = 16.dp)
			) {
				Spacer(modifier = Modifier.height(15.dp))
				
				SearchBar()
				
				Spacer(modifier = Modifier.height(10.dp))
				
				LazyRow(
					contentPadding = PaddingValues(horizontal = 10.dp),
					horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					items(sourceList.size) { index ->
						val category = sourceList[index]
						val isSelected = selectedTab == index
						TintedTextButton(
							isSelected = isSelected,
							category = category,
							onClick = { selectedTab = index }
						)
					}
				}
				
				Spacer(modifier = Modifier.height(20.dp))
				
				// Add RecyclerView here for the news articles
				NewsList()
			}
		}
	)
}

@Composable
fun NewsList() {
	val newsList = generateDummyNewsList()
	
	LazyColumn {
		items(newsList) { news ->
			NewsItem(news = news)
			Spacer(modifier = Modifier.height(16.dp))
		}
	}
}

@Composable
fun NewsItem(news: News) {
	Card(
		modifier = Modifier
			.padding(1.dp)
			.fillMaxWidth()
	) {
		Row(
			modifier = Modifier
				.padding(5.dp)
				.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
		) {
			Image(
				painter = painterResource(id = news.imageResId), // Replace with your placeholder image resource
				contentDescription = news.title,
				modifier = Modifier
					.size(120.dp)
					.clip(RoundedCornerShape(8.dp)),
				contentScale = ContentScale.Crop
			)
			
			Spacer(modifier = Modifier.width(16.dp))
			
			Column(modifier = Modifier.weight(1f)) {
				Text(
					text = news.category,
					fontSize = 12.sp,
					color = Color.Gray,
					fontWeight = FontWeight.Bold,
				)
				Spacer(modifier = Modifier.height(4.dp))
				Text(
					text = news.title,
					fontWeight = FontWeight.Bold,
					fontSize = 18.sp,
					color = Color.Black,
				)
				Spacer(modifier = Modifier.height(4.dp))
				Text(
					text = news.description,
					fontSize = 14.sp,
					color = Color.Gray,
				)
				Spacer(modifier = Modifier.height(4.dp))
				Row(verticalAlignment = Alignment.CenterVertically) {
					Text(
						text = " ${news.author} .",
						fontSize = 12.sp,
						color = Color.Gray,
						fontWeight = FontWeight.Bold,
					)
					Spacer(modifier = Modifier.width(8.dp))
					Text(
						text = news.date,
						fontSize = 12.sp,
						color = Color.Gray,
					)
				}
			}
		}
	}
}

@Composable
fun TintedTextButton(
	isSelected: Boolean,
	category: String,
	onClick: () -> Unit
) {
	val selectedBackgroundColor = colorResource(id = R.color.light_blue)
	val unselectedBackgroundColor = colorResource(id = R.color.teal_1000)
	val txtContentColor = Color.LightGray
	
	val textbgShape = RoundedCornerShape(12.dp)
	val bgColor = if (isSelected) selectedBackgroundColor else unselectedBackgroundColor
	val txtBgColor = if (!isSelected) Color.LightGray else Color.Unspecified
	
	if (isSelected) {
		TextButton(
			onClick = onClick,
			shape = textbgShape,
			colors = ButtonDefaults.buttonColors(
				backgroundColor = bgColor,
				contentColor = txtContentColor
			),
			modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp)
		) {
			Text(
				text = category,
				fontSize = 14.sp,
				fontWeight = FontWeight.Bold,
			)
		}
	} else {
		TextButton(
			onClick = onClick,
			shape = textbgShape,
			colors = ButtonDefaults.buttonColors(
				backgroundColor = unselectedBackgroundColor,
				contentColor = txtBgColor
			),
			modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp)
		) {
			Text(
				text = category,
				fontSize = 14.sp,
				fontWeight = FontWeight.Bold,
				color = txtBgColor
			)
		}
	}
}

@Composable
fun SearchBar() {
	val textState = remember { mutableStateOf(TextFieldValue()) }
	TextField(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 20.dp)
			.clip(RoundedCornerShape(18.dp)),
		value = textState.value,
		onValueChange = { textState.value = it },
		leadingIcon = {
			Icon(
				imageVector = Icons.Filled.Search,
				contentDescription = "Search Icon",
				tint = Color.Gray
			)
		},
		colors = TextFieldDefaults.textFieldColors(
			textColor = Color.DarkGray,
			backgroundColor = colorResource(id = R.color.teal_900),
			disabledIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent,
			focusedIndicatorColor = Color.Transparent
		),
		placeholder = {
			Text(text = "Tourism", color = Color.LightGray, fontSize = 16.sp)
		},
		trailingIcon = {
			IconButton(onClick = { /*TODO*/ }) {
				Icon(
					imageVector = Icons.Filled.List,
					contentDescription = "Filter Icon",
					tint = Color.Gray
				)
			}
		}
	)
}

@Preview(showBackground = true)
@Composable
fun DiscoverPreview() {
	val navController = rememberNavController()
	WorldHeadlines(navController)
}
