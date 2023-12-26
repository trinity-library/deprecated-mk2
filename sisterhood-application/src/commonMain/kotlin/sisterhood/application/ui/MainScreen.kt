package sisterhood.application.ui

import androidx.compose.runtime.Composable
import sisterhood.application.ui.hentai.HentaiGridState
import sisterhood.application.usecase.Settings
import sisterhood.presentation.HentaiInfo

@Composable
expect fun MainScreen(
    settings: Settings,
    hentaiGridState: HentaiGridState,
    naviToHentaiViewerScreen: (HentaiInfo) -> Unit,
    naviToSettingsScreen: () -> Unit,
    naviBack: () -> Unit
)
