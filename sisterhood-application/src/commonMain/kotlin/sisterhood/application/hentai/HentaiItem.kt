package sisterhood.application.hentai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sisterhood.application.ui.LoadingText
import sisterhood.domain.HentaiId
import sisterhood.usecase.HentaiInfo
import sisterhood.usecase.HentaiThumbnailInfo

const val DEFAULT_SIZE = 14f

@Composable
fun HentaiItem(
    id: HentaiId,
    fetchInfo: suspend (HentaiId) -> HentaiInfo?,
    fetchThumbnail: suspend (HentaiId) -> HentaiThumbnailInfo?
) {
    val info by produceState<HentaiInfo?>(null) {
        value = withContext(Dispatchers.IO) { fetchInfo(id) }
    }

    Column(modifier = Modifier.aspectRatio(0.625f).padding(horizontal = 4.dp)) {
        HentaiThumbnail(id, fetchThumbnail)

        // TODO: Progress History
        info?.apply {
            Spacer(modifier = Modifier.background(Color.DarkGray).fillMaxWidth().height(4.dp))
            Spacer(modifier = Modifier.height(4.dp))
        } ?: run {
            Spacer(modifier = Modifier.height(8.dp))
        }

        info?.apply {
            Text(
                text = title,
                fontSize = DEFAULT_SIZE.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
        } ?: run {
            LoadingText(fontSize = DEFAULT_SIZE.sp, length = 18)
            LoadingText(fontSize = DEFAULT_SIZE.sp, length = 18)
        }
    }

}
