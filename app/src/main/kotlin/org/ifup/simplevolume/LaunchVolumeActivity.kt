package org.ifup.simplevolume

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.AudioManager
import android.os.Bundle
import android.provider.Settings

/**
 * Invisible trampoline: opens the system volume UI and immediately finishes.
 *
 * Preference order:
 * 1. [Settings.Panel.ACTION_VOLUME] - persistent volume sliders panel (stays until dismissed).
 * 2. [AudioManager.adjustVolume] with FLAG_SHOW_UI - transient system volume HUD.
 * 3. [Settings.ACTION_SOUND_SETTINGS] - full sound settings screen.
 */
class LaunchVolumeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showVolumeUi()
        finish()
        @Suppress("DEPRECATION")
        overridePendingTransition(0, 0)
    }

    private fun showVolumeUi() {
        if (start(Settings.Panel.ACTION_VOLUME)) return

        val showedHud = runCatching {
            val audio = getSystemService(AUDIO_SERVICE) as AudioManager
            audio.adjustVolume(AudioManager.ADJUST_SAME, AudioManager.FLAG_SHOW_UI)
        }.isSuccess
        if (showedHud) return

        start(Settings.ACTION_SOUND_SETTINGS)
    }

    private fun start(action: String): Boolean = try {
        startActivity(Intent(action))
        true
    } catch (_: ActivityNotFoundException) {
        false
    } catch (_: SecurityException) {
        false
    }
}
