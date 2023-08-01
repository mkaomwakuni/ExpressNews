package dev.mkao.expressnews

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.mkao.expressnews.model.DestinationPage


@Composable
fun BottomNavigationBar(navController: NavController, appItems: List<DestinationPage>) {
	var selectedTab by remember { mutableStateOf(0) }
	BottomNavigation(
		backgroundColor = colorResource(id = R.color.white),
		contentColor = Color.DarkGray
	) {
		//
		val navBackStackEntry by navController.currentBackStackEntryAsState()
		val currentRoute = navBackStackEntry?.destination?.route
		appItems.forEachIndexed { index, item ->
			val isSelected = selectedTab == index
			val backgroundShape = if (isSelected) RoundedCornerShape(12.dp) else CircleShape
			val backgroundColor = if (isSelected) colorResource(id = R.color.light_blue) else Color.Transparent
			val contentColor = if (isSelected) Color.White else Color.DarkGray
			
			BottomNavigationItem(
				selected = isSelected,
				onClick = {
					//
					selectedTab = index
					navController.navigate(item.route) {
						navController.graph.startDestinationRoute?.let {
							DestinationPage.World.route
							popUpTo(DestinationPage.Account.route) {
								saveState = true
							}
						}
						launchSingleTop = true
						restoreState = true
						
					}
				},
				label = {
					Row(
						modifier = Modifier
							.fillMaxWidth()
							.background(
								color = backgroundColor,
								shape = backgroundShape
							),
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.Center
					) {
						if (isSelected) {
							Icon(
								painterResource(id = item.icon),
								contentDescription = item.title,
								tint = if (isSelected) contentColor else Color.LightGray
							)
							Text(
								text = item.title,
								color = contentColor,
								modifier = Modifier
									.padding(top = 10.dp),
								fontSize = 10.sp
							)
						}else
						{
							Icon(
								painterResource(id = item.icon),
								contentDescription = item.title,
								tint = if (isSelected) contentColor else Color.LightGray
							)
							Text(
								text = item.title,
								color = Color.White,
								modifier = Modifier
									.padding(top = 10.dp),
								fontSize = 10.sp
							)
							
						}
					}
				},
				
				alwaysShowLabel = true,
				selectedContentColor = Color.White,
				unselectedContentColor = Color.White.copy(0.4f),
				icon = {},
				modifier = Modifier.fillMaxWidth(),
				enabled = true,
				interactionSource = MutableInteractionSource()
			)
		}
	}
}
