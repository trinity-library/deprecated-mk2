package sisterhood.application.hentai.viewer

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import sisterhood.application.from
import sisterhood.application.ui.AsyncImage
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiImage

@Composable
fun HentaiPage(id: HentaiId, pageNumber: Int, ratio: Float, fetch: suspend (HentaiId, Int) -> HentaiImage?) {
    AsyncImage(
        load = { ImageBitmap.from(fetch(id, pageNumber) ?: ByteArray(0)) },
        painterFor = { remember { BitmapPainter(it) } },
        modifier = Modifier.aspectRatio(ratio).fillMaxWidth(),
        contentDescription = "Thumbnail($id)",
        contentScale = ContentScale.Fit
    )
}
