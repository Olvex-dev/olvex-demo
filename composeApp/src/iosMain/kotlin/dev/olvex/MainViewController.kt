package dev.olvex

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.ComposeUIViewController
import dev.olvex.core.Olvex
import dev.olvex.demo.App
import dev.olvex.demo.DemoConfig

fun MainViewController() = ComposeUIViewController {
    LaunchedEffect(Unit) {
        Olvex.init(apiKey = DemoConfig.apiKey)
    }
    App()
}
