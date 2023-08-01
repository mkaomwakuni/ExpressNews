package dev.mkao.expressnews.model

import dev.mkao.expressnews.R

sealed class DestinationPage (
    val title:String,
    val route:String,
    val icon: Int) {
  // create destinations
  object Home: DestinationPage(
   route = "Homepage", icon = R.drawable.home,
   title = "Home"
  )
  object World: DestinationPage(
   route = "WorldHeadlines", icon = R.drawable.world,
   title = "World"
  )
  object Favourites: DestinationPage(
   route = "Favourites", icon = R.drawable.favourites,
   title = "Saved"
  )
  object Account : DestinationPage(
   route = "UserSettings", icon = R.drawable.account,
   title = "User"
  )

  companion object {
   val  toList = listOf(Home, World, Favourites, Account)
  }
 }

