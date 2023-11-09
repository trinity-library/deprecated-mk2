package sisterhood.application

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sisterhood.application.navi.Navi
import sisterhood.application.navi.Root

@Preview
@Composable
fun MainApp() {
    Navi(startRoute = Root / "Main") { currentRoute ->
        when(currentRoute) {
            Root / "Main" -> {
                Text("Hello, Main!")
            }

            Root / "Settings" -> {
                Text("Hello, Settings!")
            }
        }
    }
}
