package dev.mkao.expressnews.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.mkao.expressnews.di.Homepage
import dev.mkao.expressnews.di.PrefencesSettings
import dev.mkao.expressnews.model.DestinationPage


@Composable
fun BottomNavGraph (navController: NavHostController) {
	NavHost(navController, startDestination = DestinationPage.World.route){
		composable(DestinationPage.Home.route){
			Homepage()
		}
		composable(DestinationPage.Account.route){
			PrefencesSettings()
		}
		composable(DestinationPage.World.route){
			WorldHeadlines(navController)
		}
		composable(DestinationPage.Favourites.route){
			FavouritesScreen()
		}
	}
}