package sisterhood.application

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sisterhood.application.hentai.rememberHentaiGridState
import sisterhood.application.navi.Navi
import sisterhood.usecase.HentaiInfo

@Preview
@Composable
fun MainApp() {
    val hentaiGridState = rememberHentaiGridState()

    Navi(startRoute = "main") {
        naviFrom("main") {
            MainScreen(
                hentaiGridState = hentaiGridState,
                naviToHentaiViewerScreen = { hentai: HentaiInfo -> naviTo("viewer", hentai) }
            )
        }

        naviFrom("viewer") { hentai: HentaiInfo ->
            HentaiViewerScreen(
                hentai = hentai,
                naviToMainScreen = { naviTo("main") }
            )
        }
    }
}
