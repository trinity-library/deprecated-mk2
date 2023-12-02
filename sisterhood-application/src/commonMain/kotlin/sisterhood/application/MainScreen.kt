package sisterhood.application

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import sisterhood.application.hentai.HentaiPage

@Preview
@Composable
fun MainScreen() {
    Surface(Modifier.fillMaxSize()) {
        Column {
            HentaiPage()
        }
    }
}
