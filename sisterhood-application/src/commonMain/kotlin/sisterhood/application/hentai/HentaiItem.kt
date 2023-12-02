package sisterhood.application.hentai

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sisterhood.application.ui.LoadingText
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiImage
import sisterhood.usecase.HentaiInfo

const val DEFAULT_SIZE = 16.026f

@Composable
fun HentaiItem(
    id: HentaiId,
    fetchInfo: suspend (HentaiId) -> HentaiInfo?,
    fetchThumbnail: suspend (HentaiId) -> HentaiImage?
) {
    val info by produceState<HentaiInfo?>(null) {
        value = withContext(Dispatchers.IO) { fetchInfo(id) }
    }

    Column(modifier = Modifier.aspectRatio(0.625f)) {
        HentaiThumbnail(id, fetchThumbnail)

        info?.apply {
            Text(title, fontSize = DEFAULT_SIZE.sp)
            Text(language, fontSize = DEFAULT_SIZE.sp)
        } ?: run {
            LoadingText(fontSize = DEFAULT_SIZE.sp, length = 16)
            LoadingText(fontSize = DEFAULT_SIZE.sp, length = 16)
        }
    }

}
