package sisterhood.application.ui

import androidx.compose.runtime.Composable
import sisterhood.application.ui.hentai.rememberHentaiGridState
import sisterhood.application.ui.hentai.viewer.rememberHentaiPageListState
import sisterhood.application.ui.navi.Navi
import sisterhood.application.ui.theme.StaticTheme
import sisterhood.usecase.HentaiInfo

@Composable
fun MainApp(state: MainAppState = rememberMainAppState(), finishApp: () -> Unit) {
    val hentaiGridState = rememberHentaiGridState()
    val settings = state.settings

    StaticTheme(settings = settings) {
        Navi(initialRoute = "main") {
            naviFrom("main") {
                MainScreen(
                    settings = settings,
                    hentaiGridState = hentaiGridState,
                    naviToHentaiViewerScreen = { hentai: HentaiInfo -> naviTo("viewer", hentai) },
                    naviToSettingsScreen = { naviTo("settings") },
                    naviBack = finishApp
                )
            }

            naviFrom("viewer") { hentaiInfo: HentaiInfo ->
                val hentaiPageListState = rememberHentaiPageListState(initialHentaiInfo = hentaiInfo)

                HentaiViewerScreen(
                    hentaiPageListState = hentaiPageListState,
                    naviBack = { naviBack() }
                )
            }

            naviFrom("settings") {
                SettingsScreen(
                    settings = settings,
                    changeSettings = state::changeSettings,
                    naviBack = { naviBack() }
                )
            }
        }
    }
}
