package sisterhood.application

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sisterhood.application.navi.Navi

@Preview
@Composable
fun MainApp() {
    Navi("/settings") {
        naviFrom("/main") {
            Button(onClick = { naviTo("/settings") }) {
                Text("Go to settings")
            }
        }

        naviFrom("/settings") {
            Button(onClick = { naviTo("/main") }) {
                Text("Go to main")
            }
        }
    }
}
