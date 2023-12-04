package sisterhood.domain

import kotlinx.serialization.Serializable

typealias HentaiId = Long
typealias HentaiImage = ByteArray

@Serializable
data class ImageSize(val width: Int, val height: Int) {
    val ratio: Float
        get() = width.toFloat() / height
}
