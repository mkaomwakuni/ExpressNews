package dev.mkao.expressnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import dev.mkao.expressnews.DestinationPage.*
import dev.mkao.expressnews.DestinationPage.Account.route
import dev.mkao.expressnews.ui.theme.ExpressNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    Scaffold(backgroundColor = colorResource(id = R.color.purple_200),
        topBar = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp)
                .statusBarsPadding()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "𝐄𝐗𝐏𝐑𝐄𝐒𝐒 𝐍𝐄𝐖𝐒",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
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

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.seal),
        contentColor = Color.DarkGray
    ) {
        //
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        appItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.LightGray.copy(0.4f),
                selected = currentRoute == item.route, onClick = {
                    //
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
                }
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