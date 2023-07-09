package dev.mkao.expressnews

 sealed class DestinationPage (val title:String,val icon:Int,val route:String) {
  // create destinations
  object Home:DestinationPage(
   route = "Home", icon = R.drawable.home,
   title = "Home"
  )
  object World:DestinationPage(
   route = "World", icon = R.drawable.world,
   title = "Global"
  )
  object Favourites: DestinationPage(
   route = "Favourites", icon = R.drawable.favourites,
   title = "Saved"
  )
  object Account : DestinationPage(
   route = "User", icon = R.drawable.account,
   title = "Account"
  )

  companion object {
   val  toList = listOf(Home,World,Favourites,Account)
  }
 }

