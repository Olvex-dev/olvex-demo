package dev.olvex.demo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.olvex.core.Olvex

private val Green = Color(0xFF4ade80)
private val Blue = Color(0xFF60a5fa)
private val Red = Color(0xFFf87171)
private val DarkBg = Color(0xFF060a06)
private val CardBg = Color(0xFF0d160d)
private val Muted = Color(0xFF3a5c3a)
private val SubMuted = Color(0xFF6b7c6b)

@Composable
fun App() {
    var customEventName by remember { mutableStateOf("") }
    var log by remember { mutableStateOf(listOf<String>()) }

    fun addLog(msg: String) {
        log = listOf(msg) + log
    }

    MaterialTheme {
        Surface(color = DarkBg, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .systemBarsPadding()
            ) {
                // Header
                Text(
                    "olvex",
                    color = Green,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    "SDK Demo",
                    color = SubMuted,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // ── Sessions ──────────────────────────────────────────
                SectionLabel("Session")
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OlvexButton("Start Session", Green) {
                        Olvex.track("session_start")
                        addLog("✓ Session started")
                    }
                    OlvexButton("End Session", Green) {
                        Olvex.track("session_end")
                        addLog("✓ Session ended")
                    }
                }

                Spacer(Modifier.height(24.dp))

                // ── Custom event ───────────────────────────────────────
                SectionLabel("Custom Event")
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = customEventName,
                    onValueChange = { customEventName = it },
                    placeholder = { Text("event_name", color = Color(0xFF4a5c4a)) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Green,
                        unfocusedBorderColor = Color(0xFF2a3c2a),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Green
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OlvexButton(
                    text = "Track Event",
                    color = Blue,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val name = customEventName.trim()
                    if (name.isNotBlank()) {
                        Olvex.track(name)
                        addLog("✓ CustomEvent '$name' sent")
                        customEventName = ""
                    }
                }

                Spacer(Modifier.height(24.dp))

                // ── Crash ──────────────────────────────────────────────
                SectionLabel("Crash")
                Spacer(Modifier.height(8.dp))
                OlvexButton(
                    text = "Force Crash",
                    color = Red,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    addLog("💥 Forcing crash — relaunch to see it in dashboard")
                    throw RuntimeException("Olvex test crash — intentional")
                }

                Spacer(Modifier.height(32.dp))

                // ── Event log ──────────────────────────────────────────
                SectionLabel("Log")
                Spacer(Modifier.height(8.dp))
                Surface(
                    color = CardBg,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    LazyColumn(modifier = Modifier.padding(12.dp)) {
                        if (log.isEmpty()) {
                            item {
                                Text(
                                    "No events yet. Try the buttons above.",
                                    color = Muted,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                        items(log) { entry ->
                            Text(
                                entry,
                                color = SubMuted,
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text.uppercase(),
        color = Muted,
        fontSize = 11.sp,
        fontFamily = FontFamily.Monospace,
        letterSpacing = 1.sp
    )
}

@Composable
private fun OlvexButton(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = color.copy(alpha = 0.12f)
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            text,
            color = color,
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}