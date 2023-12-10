package sisterhood.application.ui

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import sisterhood.application.ui.hentai.HentaiComponent
import sisterhood.application.ui.hentai.HentaiGridState
import sisterhood.application.usecase.Settings
import sisterhood.usecase.HentaiInfo

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
actual fun MainScreen(
    settings: Settings,
    hentaiGridState: HentaiGridState,
    naviToHentaiViewerScreen: (HentaiInfo) -> Unit,
    naviToSettingsScreen: () -> Unit,
    naviBack: () -> Unit
) {
    val openQuitDialog = remember { mutableStateOf(false) }

    BackHandler { openQuitDialog.value = true }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = naviToSettingsScreen) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = ""
                )
            }
        }
    ) {
        HentaiComponent(
            hentaiGridColumns = settings.hentaiGridColumns,
            hentaiGridState = hentaiGridState,
            onPressItem = naviToHentaiViewerScreen,
        )
    }

    when {
        openQuitDialog.value -> {
            AlertDialog(
                onDismissRequest = { openQuitDialog.value = false },
                icon = { Icon(Icons.Default.Info, "") },
                title = { Text("진짜로 나감?") },
                text = { Text("나갈거면 Quit 누르기~") },
                confirmButton = {
                    TextButton(onClick = naviBack) {
                        Text("Quit")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { openQuitDialog.value = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
