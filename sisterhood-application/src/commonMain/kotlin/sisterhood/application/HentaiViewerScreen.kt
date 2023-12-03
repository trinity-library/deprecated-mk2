package sisterhood.application

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sisterhood.application.hentai.viewer.HentaiViewerComponent
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiImage
import sisterhood.usecase.HentaiInfo

@Composable
fun HentaiViewerScreen(
    id: HentaiId,
    fetchInfo: suspend (HentaiId) -> HentaiInfo?,
    fetchPage: suspend (HentaiId, Int) -> HentaiImage?
) {
    val info by produceState<HentaiInfo?>(null) {
        value = withContext(Dispatchers.IO) { fetchInfo(id) }
    }

    info?.apply {
        HentaiViewerComponent(this)
    } ?: run {
        CircularProgressIndicator()
    }
}
