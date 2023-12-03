package sisterhood.application

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sisterhood.application.navi.Navi

@Preview
@Composable
fun MainApp() {
    Navi(startRoute = "/main") {
        naviFrom("/main") {
            MainScreen()
        }

        naviFrom("/viewer") {
            SettingScreen()
        }
    }
}
