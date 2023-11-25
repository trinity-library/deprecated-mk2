package sisterhood.application.hentai

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HentaiItem(
    id: Int,
    fetchInfo: suspend (Int) -> HentaiInfo?,
    fetchThumbnail: suspend (Int) -> ByteArray
) {
    val info by produceState<HentaiInfo?>(null) {
        value = withContext(Dispatchers.IO) { fetchInfo(id) }
    }

    Column {
        HentaiThumbnail(id, fetchThumbnail)

        info?.apply {
            Text(title)
            Text(language)
        } ?: run {
            CircularProgressIndicator()
        }
    }

}
