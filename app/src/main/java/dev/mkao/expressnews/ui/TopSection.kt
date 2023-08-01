
import android.annotation.SuppressLint
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mkao.expressnews.R
import dev.mkao.expressnews.ui.NewsList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopSection() {
	var selectedImageIndex by remember { mutableStateOf(0) }

	
	Scaffold(
		backgroundColor = colorResource(id = R.color.white),
		topBar = {
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 10.dp)
					.statusBarsPadding()
			) {
				Text(
					modifier = Modifier.fillMaxWidth(),
					text = "𝐄𝐗𝐏𝐑𝐄𝐒𝐒 𝐍𝐄𝐖𝐒",
					fontSize = 21.sp,
					fontWeight = FontWeight.Bold,
					textAlign = TextAlign.Center
				)
				
				Spacer(modifier = Modifier.height(20.dp))
				TopAppBar(
					modifier = Modifier
						.padding(10.dp)
						.height(60.dp)
						.border(
							width = 0.dp,
							color = Color.Transparent,
							shape = RoundedCornerShape(12.dp)
						)
						.clip(shape = RoundedCornerShape(12.dp)),
					title = { /* Optional title content */ },
					
					navigationIcon = {
						IconButton(
							onClick = { /* Handle menu icon click */ }
						) {
							Icon(
								imageVector = Icons.Outlined.Menu,
								contentDescription = "Menu"
							)
						}
					},
					backgroundColor = Color.Transparent,
					contentColor = Color.DarkGray,
					elevation = 0.dp,
					actions = {
						IconButton(
							onClick = { /* Handle search icon click */ }
						) {
							Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
						}
						IconButton(
							onClick = { /* Handle notification icon click */ }
						) {
							Icon(
								imageVector = Icons.Filled.Notifications,
								contentDescription = "Notifications"
							)
						}
					}
				)
				Spacer(modifier = Modifier.height(15.dp))
				Row(
					modifier = Modifier
						.padding(start = 5.dp, end = 5.dp)
						.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					// Add Breaking News headline below TopAppBar
					Text(
						text = "Breaking News",
						fontSize = 24.sp,
						fontWeight = FontWeight.Bold,
						modifier = Modifier.padding(start = 16.dp)
					)
					TextButton(
						onClick = { /*TODO*/ },
						modifier = Modifier.padding(end = 5.dp)
					) {
						Text(text = "View All", color = colorResource(id = R.color.light_blue))
					}
				}
				Spacer(modifier = Modifier.height(10.dp))
				val imageList = listOf(
					R.drawable.images9,
					R.drawable.images6,
					R.drawable.images5,
					R.drawable.images4,
					// Add more image resources here...
				)
				
				var previousSelectedImageIndex by remember { mutableStateOf(0) }
				val animateSelectedImageIndex by animateIntAsState(targetValue = selectedImageIndex)
				
				LazyColumn {
					item {
						LazyRow(
							contentPadding = PaddingValues(horizontal = 10.dp),
							horizontalArrangement = Arrangement.SpaceBetween
						) {
							items(imageList.size) { index ->
								val imageResource = imageList[index]
								val isSelected = index == animateSelectedImageIndex
								
								Box(
									modifier = Modifier
										.padding(4.dp)
										.clip(RoundedCornerShape(18.dp))
										.height(if (isSelected) 210.dp else 150.dp)
										.width(if (isSelected) 350.dp else 250.dp)
								) {
									Image(
										painter = painterResource(imageResource),
										contentDescription = "",
										contentScale = if (isSelected) ContentScale.FillBounds else ContentScale.Crop,
										modifier = Modifier
											.fillMaxSize()
											.height(if (isSelected) 160.dp else 150.dp)
											.width(if (isSelected) 280.dp else 150.dp)
											.clickable {
												selectedImageIndex = index
											}
									)
									
									if (isSelected) {
										Text(
											text = "",
											color = Color.White,
											fontSize = 20.sp,
											modifier = Modifier
												.align(Alignment.Center)
												.padding(16.dp)
										)
									}
								}
							}
						}
					}
					item {
						Spacer(modifier = Modifier.height(5.dp))
						Row(
							modifier = Modifier
								.padding(start = 5.dp, end = 5.dp)
								.fillMaxWidth(),
							horizontalArrangement = Arrangement.SpaceBetween,
							verticalAlignment = Alignment.CenterVertically
						) {
							// Add Breaking News headline below TopAppBar
							Text(
								text = "Recommendation",
								fontSize = 24.sp,
								fontWeight = FontWeight.Bold,
								modifier = Modifier.padding(start = 16.dp)
							)
							TextButton(
								onClick = { /*TODO*/ },
								modifier = Modifier.padding(end = 5.dp)
							) {
								Text(text = "View All", color = colorResource(id = R.color.light_blue))
							}
						}
						Spacer(modifier = Modifier.height(10.dp))
					}
				}
			}
		},
		content = { paddingValues ->
			val modifier = Modifier.padding(paddingValues)
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(horizontal = 16.dp)
			) {
				
				Spacer(modifier = Modifier.height(20.dp))
				
				// Add RecyclerView here for the news articles
				NewsList()
			}
		}
	)
}
@Preview
@Composable
fun TopsectPrev(){
	TopSection()
}

