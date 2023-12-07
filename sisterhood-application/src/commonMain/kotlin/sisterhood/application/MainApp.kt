package sisterhood.application

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sisterhood.application.hentai.rememberHentaiGridState
import sisterhood.application.hentai.viewer.rememberHentaiPageListState
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

        naviFrom("viewer") { hentaiInfo: HentaiInfo ->
            val hentaiPageListState = rememberHentaiPageListState(initialHentaiInfo = hentaiInfo)

            HentaiViewerScreen(
                hentaiPageListState = hentaiPageListState,
                naviToMainScreen = { naviTo("main") }
            )
        }
    }
}
