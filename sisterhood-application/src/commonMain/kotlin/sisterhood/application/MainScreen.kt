package sisterhood.application

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sisterhood.application.hentai.HentaiComponent
import sisterhood.application.hentai.HentaiGridState
import sisterhood.usecase.HentaiInfo

@Composable
fun MainScreen(
    hentaiGridState: HentaiGridState,
    naviToHentaiViewerScreen: (HentaiInfo) -> Unit
) {
    Surface(Modifier.fillMaxSize()) {
        Column {
            HentaiComponent(
                hentaiGridState = hentaiGridState,
                onPressItem = naviToHentaiViewerScreen,
            )
        }
    }
}
