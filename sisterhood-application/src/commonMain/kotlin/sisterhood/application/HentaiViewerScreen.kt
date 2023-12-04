package sisterhood.application

import androidx.compose.runtime.Composable
import sisterhood.application.hentai.viewer.HentaiViewerComponent
import sisterhood.usecase.HentaiInfo

@Composable
fun HentaiViewerScreen(hentai: HentaiInfo) {
    HentaiViewerComponent(hentai)
}
