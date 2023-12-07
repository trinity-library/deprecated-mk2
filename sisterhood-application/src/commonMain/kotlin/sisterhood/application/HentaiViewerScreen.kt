package sisterhood.application

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sisterhood.application.hentai.viewer.HentaiPageListState
import sisterhood.application.hentai.viewer.HentaiViewerComponent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HentaiViewerScreen(
    hentaiPageListState: HentaiPageListState,
    naviToMainScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(hentaiPageListState.hentaiInfo?.title ?: "") },
                navigationIcon = {
                    IconButton(onClick = naviToMainScreen) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                }
            )
        },
    ) {
        Column {
            HentaiViewerComponent(hentaiPageListState = hentaiPageListState)
        }
    }
}
