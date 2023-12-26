package sisterhood.application.ui.hentai

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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
import sisterhood.domain.HentaiImage
import sisterhood.presentation.HentaiInfo

const val DEFAULT_SIZE = 14f

@Composable
fun HentaiItem(
    id: HentaiId,
    fetchInfo: suspend (HentaiId) -> HentaiInfo?,
    fetchThumbnail: suspend (HentaiId) -> HentaiImage?,
    onPress: (HentaiInfo) -> Unit,
) {
    val hentaiInfo by produceState<HentaiInfo?>(null) {
        value = withContext(Dispatchers.IO) { fetchInfo(id) }
    }

    Card(
        modifier = Modifier
            .aspectRatio(0.625f)
            .clickable { hentaiInfo?.also { onPress(it) } }
            .padding(horizontal = 4.dp),
        border = null
    ) {
        Column {
            HentaiThumbnail(id = id, fetch = fetchThumbnail)

            // TODO: Progress History
            hentaiInfo?.apply {
                Spacer(modifier = Modifier.background(Color.DarkGray).fillMaxWidth().height(4.dp))
                Spacer(modifier = Modifier.height(4.dp))
            } ?: run {
                Spacer(modifier = Modifier.height(8.dp))
            }

            hentaiInfo?.apply {
                Text(
                    text = title,
                    fontSize = DEFAULT_SIZE.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )
            } ?: run {
                LoadingText(fontSize = DEFAULT_SIZE.sp, length = 20)
                LoadingText(fontSize = DEFAULT_SIZE.sp, length = 20)
            }
        }
    }


}
