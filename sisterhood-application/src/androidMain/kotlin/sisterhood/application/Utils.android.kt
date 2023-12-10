package sisterhood.application

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun ImageBitmap.Companion.from(bytes: ByteArray) =
    BitmapFactory.decodeByteArray(bytes, 0, bytes.size).asImageBitmap()
