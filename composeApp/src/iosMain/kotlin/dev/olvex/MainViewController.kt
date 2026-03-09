package dev.olvex

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.ComposeUIViewController
import dev.olvex.core.Olvex
import dev.olvex.demo.App
import dev.olvex.demo.DemoConfig

fun MainViewController() = ComposeUIViewController {
    LaunchedEffect(Unit) {
        runCatching {
            Olvex.init(apiKey = DemoConfig.apiKey)
        }.onFailure { error ->
            println("OlvexDemo: init failed on iOS: ${error.message}")
        }
    }
    App()
}
