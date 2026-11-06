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
import kotlin.math.abs

/**
 * One complete look. A preset sets every value below in one tap.
 * Width, height and position are never part of a look: they depend on the phone's camera cutout.
 */
data class IslandLook(
    val pillColor: Long,
    val cornerRadius: Float,
    val opacity: Float,
    val enableShadow: Boolean,
    val shadowElevation: Float,
    val blurEnabled: Boolean,
    val blurRadius: Float,
    val gradientEnabled: Boolean,
    val gradientColor: Long,
    val gradientStrength: Float,
    val accent: Long,
    val expandedTheme: String,
    val font: String,
    val animSpeed: String,
    val haptic: String,
    val mediaBlurEnabled: Boolean,
    val mediaBlurRadius: Float,
    val mediaOpacity: Float,
    val musicColor: Long,
    val callColor: Long,
    val batteryColor: Long,
    val navigationColor: Long
) {
    /** True when the phone's current settings are this look (enough fields are compared to tell the presets apart). */
    fun isActiveIn(s: SmartIslandSettings): Boolean =
        !s.materialYou &&
            s.pillColor == pillColor &&
            s.accentColor == accent &&
            s.islandFont == font &&
            s.expandedTheme == expandedTheme &&
            s.expandedGradientEnabled == gradientEnabled &&
            (!gradientEnabled || s.expandedGradientColor == gradientColor) &&
            s.expandedBlurEnabled == blurEnabled &&
            abs(s.cornerRadius - cornerRadius) < 0.5f
}

data class IslandThemePreset(
    val id: String,
    val name: String,
    val tagline: String,
    val look: IslandLook
)

object IslandThemePresets {
    val all: List<IslandThemePreset> = listOf(
        IslandThemePreset(
            id = "ios_glass",
            name = "iOS Glass",
            tagline = "Pure black pill, frosted card, album-art blur",
            look = IslandLook(
                pillColor = 0xFF000000L, cornerRadius = 40f, opacity = 1f,
                enableShadow = true, shadowElevation = 12f,
                blurEnabled = true, blurRadius = 28f,
                gradientEnabled = false, gradientColor = 0xFF0A84FFL, gradientStrength = 0.5f,
                accent = 0xFF0A84FFL, expandedTheme = SmartIslandSettings.EXPANDED_THEME_DARK,
                font = SmartIslandSettings.FONT_INTER_DISPLAY,
                animSpeed = AnimationSpeed.ID_NORMAL, haptic = HapticStrength.ID_NORMAL,
                mediaBlurEnabled = true, mediaBlurRadius = 36f, mediaOpacity = 0.96f,
                musicColor = 0xFFFF375FL, callColor = 0xFF30D158L, batteryColor = 0xFF30D158L, navigationColor = 0xFF0A84FFL
            )
        ),
        IslandThemePreset(
            id = "oneui_now",
            name = "One UI Now",
            tagline = "Soft blue glow, follows the phone's light/dark mode",
            look = IslandLook(
                pillColor = 0xFF0B0B0DL, cornerRadius = 30f, opacity = 1f,
                enableShadow = true, shadowElevation = 10f,
                blurEnabled = true, blurRadius = 32f,
                gradientEnabled = true, gradientColor = 0xFF3E91FFL, gradientStrength = 0.35f,
                accent = 0xFF3E91FFL, expandedTheme = SmartIslandSettings.EXPANDED_THEME_SYSTEM,
                font = SmartIslandSettings.FONT_SYSTEM,
                animSpeed = AnimationSpeed.ID_SNAPPY, haptic = HapticStrength.ID_LIGHT,
                mediaBlurEnabled = true, mediaBlurRadius = 40f, mediaOpacity = 0.95f,
                musicColor = 0xFF3E91FFL, callColor = 0xFF2ECC71L, batteryColor = 0xFF2ECC71L, navigationColor = 0xFF3E91FFL
            )
        ),
        IslandThemePreset(
            id = "amoled_minimal",
            name = "AMOLED Minimal",
            tagline = "True black, no glow, lightest on battery",
            look = IslandLook(
                pillColor = 0xFF000000L, cornerRadius = 24f, opacity = 1f,
                enableShadow = false, shadowElevation = 0f,
                blurEnabled = false, blurRadius = 24f,
                gradientEnabled = false, gradientColor = 0xFF9CA3AFL, gradientStrength = 0.3f,
                accent = 0xFFB8C0CCL, expandedTheme = SmartIslandSettings.EXPANDED_THEME_DARK,
                font = SmartIslandSettings.FONT_INTER_DISPLAY,
                animSpeed = AnimationSpeed.ID_SNAPPY, haptic = HapticStrength.ID_LIGHT,
                mediaBlurEnabled = false, mediaBlurRadius = 24f, mediaOpacity = 1f,
                musicColor = 0xFFE5E7EBL, callColor = 0xFF22C55EL, batteryColor = 0xFF22C55EL, navigationColor = 0xFF60A5FAL
            )
        ),
        IslandThemePreset(
            id = "aurora",
            name = "Aurora",
            tagline = "Violet gradient, deep blur, unhurried motion",
            look = IslandLook(
                pillColor = 0xFF0B0A14L, cornerRadius = 34f, opacity = 1f,
                enableShadow = true, shadowElevation = 16f,
                blurEnabled = true, blurRadius = 40f,
                gradientEnabled = true, gradientColor = 0xFF7C3AEDL, gradientStrength = 0.65f,
                accent = 0xFF8B5CF6L, expandedTheme = SmartIslandSettings.EXPANDED_THEME_DARK,
                font = SmartIslandSettings.FONT_POPPINS,
                animSpeed = AnimationSpeed.ID_RELAXED, haptic = HapticStrength.ID_NORMAL,
                mediaBlurEnabled = true, mediaBlurRadius = 44f, mediaOpacity = 0.92f,
                musicColor = 0xFFA78BFAL, callColor = 0xFF34D399L, batteryColor = 0xFF34D399L, navigationColor = 0xFF38BDF8L
            )
        ),
        IslandThemePreset(
            id = "neon_cyber",
            name = "Neon Cyber",
            tagline = "Cyan glow, sharp corners, mono font, strong haptics",
            look = IslandLook(
                pillColor = 0xFF05070AL, cornerRadius = 16f, opacity = 1f,
                enableShadow = true, shadowElevation = 22f,
                blurEnabled = true, blurRadius = 20f,
                gradientEnabled = true, gradientColor = 0xFF06B6D4L, gradientStrength = 0.55f,
                accent = 0xFF22D3EEL, expandedTheme = SmartIslandSettings.EXPANDED_THEME_DARK,
                font = SmartIslandSettings.FONT_MONOSPACE,
                animSpeed = AnimationSpeed.ID_SNAPPY, haptic = HapticStrength.ID_STRONG,
                mediaBlurEnabled = true, mediaBlurRadius = 24f, mediaOpacity = 0.94f,
                musicColor = 0xFF22D3EEL, callColor = 0xFF4ADE80L, batteryColor = 0xFFFACC15L, navigationColor = 0xFFF472B6L
            )
        ),
        IslandThemePreset(
            id = "sunset",
            name = "Sunset",
            tagline = "Warm orange glow with rounded, friendly type",
            look = IslandLook(
                pillColor = 0xFF120A08L, cornerRadius = 36f, opacity = 1f,
                enableShadow = true, shadowElevation = 16f,
                blurEnabled = true, blurRadius = 32f,
                gradientEnabled = true, gradientColor = 0xFFFF7A18L, gradientStrength = 0.6f,
                accent = 0xFFFF7849L, expandedTheme = SmartIslandSettings.EXPANDED_THEME_DARK,
                font = SmartIslandSettings.FONT_POPPINS,
                animSpeed = AnimationSpeed.ID_NORMAL, haptic = HapticStrength.ID_NORMAL,
                mediaBlurEnabled = true, mediaBlurRadius = 36f, mediaOpacity = 0.94f,
                musicColor = 0xFFFFB347L, callColor = 0xFF4ADE80L, batteryColor = 0xFFFFB347L, navigationColor = 0xFFFF7849L
            )
        ),
        IslandThemePreset(
            id = "paper_light",
            name = "Paper Light",
            tagline = "Light expanded card, crisp and calm",
            look = IslandLook(
                pillColor = 0xFF111113L, cornerRadius = 30f, opacity = 1f,
                enableShadow = true, shadowElevation = 8f,
                blurEnabled = true, blurRadius = 28f,
                gradientEnabled = false, gradientColor = 0xFF2563EBL, gradientStrength = 0.4f,
                accent = 0xFF2563EBL, expandedTheme = SmartIslandSettings.EXPANDED_THEME_LIGHT,
                font = SmartIslandSettings.FONT_INTER_DISPLAY,
                animSpeed = AnimationSpeed.ID_NORMAL, haptic = HapticStrength.ID_LIGHT,
                mediaBlurEnabled = true, mediaBlurRadius = 32f, mediaOpacity = 0.96f,
                musicColor = 0xFFEF4444L, callColor = 0xFF16A34AL, batteryColor = 0xFF16A34AL, navigationColor = 0xFF2563EBL
            )
        )
    )
}
