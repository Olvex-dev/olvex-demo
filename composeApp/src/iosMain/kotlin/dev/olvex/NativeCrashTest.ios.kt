package dev.olvex.demo

import platform.posix.SIGABRT
import platform.posix.raise

actual val supportsNativeSignalCrashTest: Boolean = true

actual fun triggerNativeSignalCrash() {
    raise(SIGABRT)
}
