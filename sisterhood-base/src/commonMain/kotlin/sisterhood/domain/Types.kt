package sisterhood.domain

typealias HentaiId = Long
typealias HentaiImage = ByteArray

data class ImageSize(val width: Int, val height: Int) {
    val ratio: Float
        get() = width.toFloat() / height
}
