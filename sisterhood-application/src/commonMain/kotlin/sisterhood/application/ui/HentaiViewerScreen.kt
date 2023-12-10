package sisterhood.application.ui

import androidx.compose.runtime.Composable
import sisterhood.application.ui.hentai.viewer.HentaiPageListState

@Composable
expect fun HentaiViewerScreen(
    hentaiPageListState: HentaiPageListState,
    naviBack: () -> Unit
)
