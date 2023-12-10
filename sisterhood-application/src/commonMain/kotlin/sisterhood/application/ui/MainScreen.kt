package sisterhood.application.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sisterhood.application.ui.hentai.HentaiComponent
import sisterhood.application.ui.hentai.HentaiGridState
import sisterhood.usecase.HentaiInfo

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    hentaiGridState: HentaiGridState,
    naviToHentaiViewerScreen: (HentaiInfo) -> Unit,
    naviToSettingsScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = naviToSettingsScreen) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = ""
                )
            }
        }
    ) {
        Column {
            HentaiComponent(
                hentaiGridState = hentaiGridState,
                onPressItem = naviToHentaiViewerScreen,
            )
        }
    }
}
