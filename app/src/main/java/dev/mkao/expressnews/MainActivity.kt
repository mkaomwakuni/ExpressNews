 package dev.mkao.expressnews

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
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
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.mkao.expressnews.DestinationPage.*
import dev.mkao.expressnews.DestinationPage.Account.route
import dev.mkao.expressnews.ui.theme.ExpressNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Change the status bar icons color to black
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView)
            .isAppearanceLightStatusBars = true
        setContent {
            ExpressNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    Categories()
                }
            }
        }
    }

}

@Composable
fun Categories() {
    val sourcesCategory = listOf("Technology","Sports","Politics","Finance","War","World")
    val navController = rememberNavController()
    Scaffold(backgroundColor = colorResource(id = R.color.white),
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
                    fontSize = 18.sp,
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
                            Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Search")
                        }
                    }
                )
            }
        },
        bottomBar ={ BottomNavigationBar(navController = navController, appItems = DestinationPage.toList)},
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavigationMechanism(navController = navController)
            }
        }
    )
}

@Composable
fun NavigationMechanism(navController: NavHostController) {
    NavHost(navController, startDestination = DestinationPage.Home.route){
        composable(Home.route){
            Home()
        }
        composable(Account.route){
            Account()
        }
        composable(World.route){
            World()
        }
        composable(Favourites.route){
            Favourites()
        }

    }

}

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
                            route
                            popUpTo(route) {
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ExpressNewsTheme {
        Categories()
    }
}