# Simple Volume

Tiny Android app for devices without hardware volume buttons (e.g. Supernote Manta).
Launching it opens the system volume panel and immediately exits. No permissions required.

## Build

Requires a JDK (21+) and the Android SDK.

    ./gradlew assembleDebug
    adb install app/build/outputs/apk/debug/app-debug.apk

## Behavior

1. Opens `Settings.Panel.ACTION_VOLUME` (persistent sliders panel).
2. Falls back to the system volume HUD (`AudioManager.adjustVolume` + `FLAG_SHOW_UI`).
3. Falls back to the Sound settings screen.
