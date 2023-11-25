package sisterhood.application

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sisterhood.application.navi.Navi
import sisterhood.application.ui.Home

@Preview
@Composable
fun MainApp() {
    Navi("/main") {
        naviFrom("/main") {
            Home()
        }

        naviFrom("/settings") {
            Button(onClick = { naviTo("/main") }) {
                Text("Go to main")
            }
        }
    }
}
