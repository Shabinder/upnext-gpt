package io.upnextgpt.ui.settings.viewmodel

import androidx.compose.runtime.Immutable

@Immutable
data class SettingsUiState(
    val apiBaseUrl: String? = null,
    val isTestingApiBaseUrl: Boolean = false,
    val testResultMessage: String? = null,
    val isApiBaseUrlWorkingProperly: Boolean? = null,
)