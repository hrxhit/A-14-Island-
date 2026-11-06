/*
 * Smart Island (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the GNU GPL v3 License
 * Do not remove or alter this notice. - Per GPL-3.0 Section 4 & Section 5
 */

package com.agupta07505.smartisland.ui.sections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agupta07505.smartisland.data.SmartIslandSettings
import com.agupta07505.smartisland.data.SmartIslandSettingsRepository
import com.agupta07505.smartisland.model.IslandLook
import com.agupta07505.smartisland.model.IslandThemePreset
import com.agupta07505.smartisland.model.IslandThemePresets
import kotlinx.coroutines.launch

// Settings > Customizations: one-tap looks. A preset only calls the same setters the sliders and pickers below use.

/** Writes every value of [look] through the normal setters (each one validates and clamps its own value). */
internal suspend fun SmartIslandSettingsRepository.applyLook(look: IslandLook) {
    setMaterialYou(false)
    setPillColor(look.pillColor)
    setCornerRadius(look.cornerRadius)
    setOpacity(look.opacity)
    setEnableShadow(look.enableShadow)
    setShadowElevation(look.shadowElevation)
    setExpandedBlurEnabled(look.blurEnabled)
    setExpandedBlurRadius(look.blurRadius)
    setExpandedGradientEnabled(look.gradientEnabled)
    setExpandedGradientColor(look.gradientColor)
    setExpandedGradientStrength(look.gradientStrength)
    setAccentColor(look.accent)
    setExpandedTheme(look.expandedTheme)
    setIslandFont(look.font)
    setAnimSpeedPortrait(look.animSpeed)
    setAnimSpeedLandscape(look.animSpeed)
    setHapticStrength(look.haptic)
    setMediaBlurEnabled(look.mediaBlurEnabled)
    setMediaBlurRadius(look.mediaBlurRadius)
    setMediaOpacity(look.mediaOpacity)
    setMusicVisualizerColor(look.musicColor)
    setCallColor(look.callColor)
    setBatteryColor(look.batteryColor)
    setNavigationColor(look.navigationColor)
}

@Composable
fun ThemePresetsCard(
    settings: SmartIslandSettings,
    repository: SmartIslandSettingsRepository
) {
    val scope = rememberCoroutineScope()
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "THEME PRESETS",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 0.8.sp
            )
            Text(
                text = "One tap sets colours, blur, glow, font, motion and haptics together. Size and position stay as you set them; you can still fine-tune everything below.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                IslandThemePresets.all.forEach { preset ->
                    PresetTile(
                        preset = preset,
                        active = preset.look.isActiveIn(settings),
                        onClick = { scope.launch { repository.applyLook(preset.look) } }
                    )
                }
            }
        }
    }
}

@Composable
private fun PresetTile(
    preset: IslandThemePreset,
    active: Boolean,
    onClick: () -> Unit
) {
    val look = preset.look
    val tileShape = RoundedCornerShape(16.dp)
    val pill = Color(look.pillColor.toInt())
    val accent = Color(look.accent.toInt())
    val stage = if (look.expandedTheme == SmartIslandSettings.EXPANDED_THEME_LIGHT) Color(0xFFE9E7EC) else Color(0xFF16161A)
    val card = if (look.gradientEnabled) {
        Brush.horizontalGradient(listOf(pill, Color(look.gradientColor.toInt()).copy(alpha = look.gradientStrength)))
    } else {
        Brush.horizontalGradient(listOf(pill, pill))
    }
    Column(
        modifier = Modifier
            .width(142.dp)
            .clip(tileShape)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
            .border(
                BorderStroke(
                    if (active) 2.dp else 1.dp,
                    if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
                ),
                tileShape
            )
            .clickable(onClick = onClick)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // A tiny sketch of the look: the pill on top, the expanded card (with its glow) underneath, the accent as a dot.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(stage),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .width(44.dp)
                        .height(13.dp)
                        .clip(CircleShape)
                        .background(pill)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(26.dp)
                        .clip(RoundedCornerShape((look.cornerRadius / 3f).dp))
                        .background(card),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(accent)
                    )
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = preset.name,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (active) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Text(
            text = preset.tagline,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
