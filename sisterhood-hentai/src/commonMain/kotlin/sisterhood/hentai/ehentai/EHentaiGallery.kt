package sisterhood.hentai.ehentai

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EHentaiGallery(
    @SerialName("gid")
    val id: Int,
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
    @SerialName("filecount")
    val fileCount: String,
    @SerialName("filesize")
    val fileSize: Long,
    val expunged: Boolean,
    val rating: String,
    @SerialName("torrentcount")
    val torrentCount: String,
    val torrents: List<EHentaiTorrent>,
    val tags: List<String>,
    @SerialName("parent_gid")
    val parentID: String,
    @SerialName("parent_key")
    val parentKey: String,
    @SerialName("first_gid")
    val firstID: String,
    @SerialName("first_key")
    val firstKey: String,
)
