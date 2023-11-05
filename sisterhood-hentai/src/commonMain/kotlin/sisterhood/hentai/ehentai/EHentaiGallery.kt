package sisterhood.hentai.ehentai

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EHentaiGallery(
    val gid: Int,
    val token: String,
    @SerialName("archiver_key")
    val archiverKey: String,
    val title: String,
    @SerialName("title_jpn")
    val titleJpn: String,
    val category: String,
    val thumb: String,
    val uploader: String,
    val posted: String,
    val filecount: String,
    val filesize: Long,
    val expunged: Boolean,
    val rating: String,
    val torrentcount: String,
    val torrents: List<EHentaiTorrent>,
    val tags: List<String>,
    @SerialName("parent_gid")
    val parentGid: String,
    @SerialName("parent_key")
    val parentKey: String,
    @SerialName("first_gid")
    val firstGid: String,
    @SerialName("first_key")
    val firstKey: String,
)
