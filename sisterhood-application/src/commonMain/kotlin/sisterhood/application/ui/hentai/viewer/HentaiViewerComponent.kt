package sisterhood.application.ui.hentai.viewer

import androidx.compose.runtime.Composable

@Composable
fun HentaiViewerComponent(
    hentaiPageListState: HentaiPageListState
) {
    HentaiPageList(state = hentaiPageListState)
}
