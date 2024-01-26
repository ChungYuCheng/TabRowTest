package com.example.tabrowtest

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tabrowtest.ui.theme.TabRowTestTheme
import com.google.android.material.color.MaterialColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabRowTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TabScreen(1)
                }
            }
        }
    }
}

@UiComposable
@Composable
fun TabScreen(tab: Int) {
    var tabIndex by remember { mutableIntStateOf(tab) }

    val tabs = listOf("Home", "About", "Settings")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                ) {
                    Text(
                        text = title,
//                        style = MaterialTheme.typography.headlineSmall
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
        when (tabIndex) {
            0 -> HomeScreen()
            1 -> AboutScreen()
            2 -> SettingsScreen()
        }
    }
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen() {
    val rows = 3
    val columns = 3

    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = rows
    ) {
        val itemModifier = Modifier
            .padding(4.dp)
            .height(80.dp)
            .weight(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.onTertiaryContainer)
//        repeat(rows * columns) {
        repeat(10) { index ->
            Box(
                modifier = itemModifier,
            ) {
                Text(
                    text = "$index",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AboutScreen() {
    val rows = 3
    val columns = 3

    val borderWidth = 4.dp

    val rainbowColorBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }

    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = rows
    ) {
        repeat(10) { index ->
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "",
                modifier = Modifier
                    .clip(CircleShape)
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.onTertiaryContainer)
                    .border(
                        BorderStroke(borderWidth, rainbowColorBrush),
                        CircleShape
                    ).aspectRatio(16f / 9f)
                    .blur(radius = 8.dp, edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun SettingsScreen() {
    Column {
        Text(text = "SETTING")
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun TabRowPreview() {
    TabRowTestTheme {
        TabScreen(1)
    }
}