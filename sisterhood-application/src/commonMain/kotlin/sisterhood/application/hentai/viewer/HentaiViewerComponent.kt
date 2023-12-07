package sisterhood.application.hentai.viewer

import androidx.compose.runtime.Composable

@Composable
fun HentaiViewerComponent(
    hentaiPageListState: HentaiPageListState
) {
    HentaiPageList(state = hentaiPageListState)
}
