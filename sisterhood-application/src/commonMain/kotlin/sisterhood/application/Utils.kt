package sisterhood.application

import androidx.compose.ui.graphics.ImageBitmap

expect fun ImageBitmap.Companion.from(bytes: ByteArray): ImageBitmap
