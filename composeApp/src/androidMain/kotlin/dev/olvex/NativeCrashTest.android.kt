package dev.olvex.demo

actual val supportsNativeSignalCrashTest: Boolean = false

actual fun triggerNativeSignalCrash() {
    error("Native signal crash test is only available on iOS")
}
