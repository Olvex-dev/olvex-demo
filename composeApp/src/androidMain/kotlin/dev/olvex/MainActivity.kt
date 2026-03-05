package dev.olvex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.olvex.core.AndroidOlvex
import dev.olvex.demo.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        AndroidOlvex.init(
            context = this,
            apiKey = "YOUR_OLVEX_API_KEY" // Get your key at olvex.dev
        )

        setContent {
            App()
        }
    }
}
