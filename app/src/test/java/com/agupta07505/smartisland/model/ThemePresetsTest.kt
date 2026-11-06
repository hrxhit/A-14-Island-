/*
 * Smart Island (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the GNU GPL v3 License
 * Do not remove or alter this notice. - Per GPL-3.0 Section 4 & Section 5
 */

package com.agupta07505.smartisland.model

import com.agupta07505.smartisland.data.SmartIslandSettings
import com.agupta07505.smartisland.util.AnimationSpeed
import com.agupta07505.smartisland.util.HapticStrength
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ThemePresetsTest {
    @Test
    fun idsAreUniqueAndNotBlank() {
        val ids = IslandThemePresets.all.map { it.id }
        assertTrue(ids.all { it.isNotBlank() })
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun everyValueIsInsideTheRangeTheSettingAccepts() {
        IslandThemePresets.all.forEach { preset ->
            val l = preset.look
            val tag = preset.id
            assertTrue("$tag corner", l.cornerRadius in SmartIslandSettings.MIN_CORNER_RADIUS..SmartIslandSettings.MAX_CORNER_RADIUS)
            assertTrue("$tag opacity", l.opacity in SmartIslandSettings.MIN_OPACITY..SmartIslandSettings.MAX_OPACITY)
            assertTrue("$tag shadow", l.shadowElevation in SmartIslandSettings.MIN_SHADOW_ELEVATION..SmartIslandSettings.MAX_SHADOW_ELEVATION)
            assertTrue("$tag blur", l.blurRadius in SmartIslandSettings.MIN_EXPANDED_BLUR..SmartIslandSettings.MAX_EXPANDED_BLUR)
            assertTrue("$tag media blur", l.mediaBlurRadius in SmartIslandSettings.MIN_EXPANDED_BLUR..SmartIslandSettings.MAX_EXPANDED_BLUR)
            assertTrue("$tag gradient", l.gradientStrength in SmartIslandSettings.MIN_GRADIENT_STRENGTH..SmartIslandSettings.MAX_GRADIENT_STRENGTH)
            assertTrue("$tag media opacity", l.mediaOpacity in 0.2f..1f)
        }
    }

    @Test
    fun everyIdIsOneTheAppKnows() {
        IslandThemePresets.all.forEach { preset ->
            val l = preset.look
            assertTrue(preset.id, l.font in SmartIslandSettings.VALID_FONTS)
            assertTrue(preset.id, l.expandedTheme in SmartIslandSettings.VALID_EXPANDED_THEMES)
            assertTrue(preset.id, l.animSpeed in AnimationSpeed.VALID_IDS)
            assertTrue(preset.id, l.haptic in HapticStrength.VALID_IDS)
        }
    }

    @Test
    fun everyColourIsFullyOpaque() {
        IslandThemePresets.all.forEach { preset ->
            val l = preset.look
            listOf(l.pillColor, l.gradientColor, l.accent, l.musicColor, l.callColor, l.batteryColor, l.navigationColor).forEach {
                assertEquals(preset.id, 0xFFL, (it shr 24) and 0xFFL)
            }
        }
    }

    @Test
    fun noTwoPresetsShareAnAccent() {
        val accents = IslandThemePresets.all.map { it.look.accent }
        assertEquals(accents.size, accents.toSet().size)
    }
}
