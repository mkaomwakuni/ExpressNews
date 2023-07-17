package dev.mkao.expressnews

import android.graphics.drawable.Icon

sealed class DestinationPage (val title:String,val route:String,val icon: Int) {
  // create destinations
  object Home:DestinationPage(
   route = "Home", icon = R.drawable.home,
   title = "Home"
  )
  object World:DestinationPage(
   route = "World", icon = R.drawable.world,
   title = "World"
  )
  object Favourites: DestinationPage(
   route = "Favourites", icon = R.drawable.favourites,
   title = "Saved"
  )
  object Account : DestinationPage(
   route = "User", icon = R.drawable.account,
   title = "User"
  )

  companion object {
   val  toList = listOf(Home,World,Favourites,Account)
  }
 }

