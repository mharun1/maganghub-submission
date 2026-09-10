package com.example.submission

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.submission.navigation.Detail
import com.example.submission.navigation.Home
import com.example.submission.ui.detail.DetailScreen
import com.example.submission.ui.list.HomeScreen
import com.example.submission.ui.theme.SubmissionTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SubmissionTheme {
                Navigation(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Home -> NavEntry(key) {
                    HomeScreen(
                        onItemClick = { id ->
                            backStack.add(Detail(id = id))
                        }
                    )
                }
                is Detail -> NavEntry(key) {
                    DetailScreen(
                        id = key.id,
                        onBack = { backStack.removeLastOrNull() }
                    )
                }
                else -> throw IllegalArgumentException("Unknown key: $key")
            }
        },
        modifier = modifier
    )
}