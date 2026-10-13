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

## Release

Pushing a tag that matches `versionName` (e.g. `v1.0.0`) runs `.github/workflows/release.yml`,
which builds a signed APK and publishes a GitHub Release. Required repository secrets:
`KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`.
