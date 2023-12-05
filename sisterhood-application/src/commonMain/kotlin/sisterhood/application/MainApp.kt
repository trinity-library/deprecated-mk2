package sisterhood.application

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sisterhood.application.navi.Navi
import sisterhood.usecase.HentaiInfo

@Preview
@Composable
fun MainApp() {
    Navi(startRoute = "main") {
        naviFrom("main") {
            MainScreen(
                naviToHentaiViewerScreen = { hentai: HentaiInfo -> naviTo("viewer", hentai) }
            )
        }

        naviFrom("viewer") { hentai: HentaiInfo ->
            HentaiViewerScreen(hentai)
        }
    }
}
