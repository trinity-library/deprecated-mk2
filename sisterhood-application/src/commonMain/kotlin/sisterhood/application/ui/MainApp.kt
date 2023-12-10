package sisterhood.application.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sisterhood.application.ui.hentai.rememberHentaiGridState
import sisterhood.application.ui.hentai.viewer.rememberHentaiPageListState
import sisterhood.application.ui.navi.Navi
import sisterhood.usecase.HentaiInfo

@Preview
@Composable
fun MainApp() {
    val hentaiGridState = rememberHentaiGridState()

    Navi(startRoute = "main") {
        naviFrom("main") {
            MainScreen(
                hentaiGridState = hentaiGridState,
                naviToHentaiViewerScreen = { hentai: HentaiInfo -> naviTo("viewer", hentai) },
                naviToSettingsScreen = { naviTo("settings") }
            )
        }

        naviFrom("viewer") { hentaiInfo: HentaiInfo ->
            val hentaiPageListState = rememberHentaiPageListState(initialHentaiInfo = hentaiInfo)

            HentaiViewerScreen(
                hentaiPageListState = hentaiPageListState,
                naviToMainScreen = { naviTo("main") }
            )
        }

        naviFrom("settings") {
            SettingsScreen(
                naviToMainScreen = { naviTo("main") }
            )
        }
    }
}
