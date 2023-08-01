package dev.mkao.expressnews.model

import dev.mkao.expressnews.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

data class News(
	val title: String,
	val description: String,
	val category: String,
	val author: String,
	val date: String,
	val imageResId: Int
	// Use image resource ID instead of imageUrl
)
val categories = listOf("Technology", "Finance", "Politics", "Sports", "War", "World")
fun generateDummyNewsList(): List<News> {
	val newsList = mutableListOf<News>()
	
	// Generate 9 more news items with auto-generated data
	val random = Random(0) // Use a seed for reproducibility
	val imageResources = listOf(
		R.drawable.images6,
		R.drawable.images2,
		R.drawable.images3,
		R.drawable.images4,
		R.drawable.images5,
		R.drawable.images6,
		R.drawable.images7,
		R.drawable.images8,
		R.drawable.images9
	)
	repeat(9) {
		val category = categories[random.nextInt(categories.size)]
		val title = getRandomTitle(random, category)
		val description = getRandomDescription(random, category)
		val author = getRandomAuthor(random)
		val date = getRandomDate(random)
		val imageResId = imageResources[it]
		newsList.add(
			News(
				title = title,
				description = description,
				category = category,
				author = author,
				date = date,
				imageResId = imageResId
			)
		)
	}
	
	return newsList
}

private fun getRandomTitle(random: Random, category: String): String {
	val technologyTitles = listOf(
		"Breaking News: New Tech Gadgets Released!",
		"Exciting Tech Announcement: The Future is Here!",
		"Latest Technology Innovations: Changing the World",
		"Tech Giants Unveil Groundbreaking Products",
		"Revolutionizing Technology: The Next Big Leap"
	)
	val financeTitles = listOf(
		"Financial Market Report: Stocks Surge",
		"Economic Growth: A Positive Outlook",
		"Finance News: Market Volatility and Trends",
		"Investment Strategies: A Guide for Traders",
		"Financial Sector: Recent Developments"
	)
	val politicsTitles = listOf(
		"Election Results: New Leadership Takes Charge",
		"Political Developments: Policy Changes Ahead",
		"Breaking News: Major Political Announcement",
		"Government Initiatives: A Focus on the Future",
		"Global Politics: Shaping International Relations"
	)
	val sportsTitles = listOf(
		"Sports Highlights: Records Smashed!",
		"Exciting Match Results: Thrilling Showdowns",
		"Sports World: Athletes Making History",
		"Champion's Glory: Victory in the Arena",
		"Sports Fans Rejoice: Memorable Moments"
	)
	val warTitles = listOf(
		"International Conflicts: A Global Perspective",
		"Breaking News: Conflict Zones Update",
		"Security Measures: Protecting Nations",
		"Warfare Strategies: A Look Into the Tactics",
		"Global Defense: Building Strong Armed Forces"
	)
	val worldTitles = listOf(
		"World Events: Impacting Societies Everywhere",
		"Global Culture: Celebrating Diversity",
		"Worldwide Discoveries: Exploring New Horizons",
		"Humanity's Journey: Progress and Challenges",
		"A Changing World: Embracing the Future"
	)
	
	return when (category) {
		"Technology" -> technologyTitles[random.nextInt(technologyTitles.size)]
		"Finance" -> financeTitles[random.nextInt(financeTitles.size)]
		"Politics" -> politicsTitles[random.nextInt(politicsTitles.size)]
		"Sports" -> sportsTitles[random.nextInt(sportsTitles.size)]
		"War" -> warTitles[random.nextInt(warTitles.size)]
		"World" -> worldTitles[random.nextInt(worldTitles.size)]
		else -> "Default News Title"
	// Default title if category not found
	}
}

private fun getRandomDescription(random: Random, category: String): String {
	val technologyDescriptions = listOf(
		"Check out the latest tech gadgets that are taking the world by storm.",
		"Discover the most advanced technologies that will shape the future.",
		"Explore the innovative breakthroughs revolutionizing the tech industry.",
		"Get a glimpse into the exciting world of technology and its possibilities.",
		"Learn about the cutting-edge tech solutions solving real-world problems."
	)
	val financeDescriptions = listOf(
		"Stay updated on the financial market with the latest news and stock trends.",
		"Explore the economic landscape and its impact on businesses and consumers.",
		"Get insights into investment opportunities and financial planning.",
		"Learn about the global economy and its interconnected dynamics.",
		"Discover the latest financial strategies and advice from experts."
	)
	val politicsDescriptions = listOf(
		"Get the most recent updates on politics, including election results and more.",
		"Stay informed about political decisions and their impact on the nation.",
		"Discover the policies and initiatives that will shape the political landscape.",
		"Explore the latest developments in national and international politics.",
		"Get insights into the decisions made by leaders around the world."
	)
	val sportsDescriptions = listOf(
		"Get the latest sports match results and highlights from around the globe.",
		"Explore the exciting world of sports and the achievements of athletes.",
		"Discover the stories of champions and their journeys to success.",
		"Stay informed about sports events that captivate audiences worldwide.",
		"Celebrate the spirit of sportsmanship and teamwork on the field."
	)
	val warDescriptions = listOf(
		"Explore the complexities of international conflicts and their implications.",
		"Stay updated on the security situations in conflict zones.",
		"Learn about the strategies employed in warfare and defense.",
		"Get insights into global security measures and policies.",
		"Understand the challenges of maintaining peace and stability in the world."
	)
	val worldDescriptions = listOf(
		"Explore world events and their impact on societies and cultures.",
		"Celebrate the diversity of cultures and traditions around the globe.",
		"Discover the latest findings and breakthroughs in global exploration.",
		"Reflect on humanity's progress and the challenges that lie ahead.",
		"Embrace the changing world and its possibilities for a better future."
	)
	
	return when (category) {
		"Technology" -> technologyDescriptions[random.nextInt(technologyDescriptions.size)]
		"Finance" -> financeDescriptions[random.nextInt(financeDescriptions.size)]
		"Politics" -> politicsDescriptions[random.nextInt(politicsDescriptions.size)]
		"Sports" -> sportsDescriptions[random.nextInt(sportsDescriptions.size)]
		"War" -> warDescriptions[random.nextInt(warDescriptions.size)]
		"World" -> worldDescriptions[random.nextInt(worldDescriptions.size)]
		else -> "Default News Description"
	// Default description if category not found
	}
}

private fun getRandomAuthor(random: Random): String {
	val authors = listOf(
		"Roseline Njau", "Jane Smith", "Michael Johnson", "Emily Williams",
		"Christopher Brown", "MkaoMediaz", "Montreal", "Lisa Martinez"
	)
	return authors[random.nextInt(authors.size)]
}
private fun getRandomDate(random: Random): String {
	val year = 2023
	val month = random.nextInt(12) + 1
	val day = random.nextInt(28) + 1
	
	val calendar = Calendar.getInstance()
	calendar.set(Calendar.YEAR, year)
	calendar.set(Calendar.MONTH, month - 1)
	// Calendar months start from 0 (January is 0)
	calendar.set(Calendar.DAY_OF_MONTH, day)
	
	val dateFormat = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())
	return dateFormat.format(calendar.time)
}