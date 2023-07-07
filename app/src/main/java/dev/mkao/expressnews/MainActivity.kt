package dev.mkao.expressnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        }) {
        Column(modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .padding(paddingValues = it)) {

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