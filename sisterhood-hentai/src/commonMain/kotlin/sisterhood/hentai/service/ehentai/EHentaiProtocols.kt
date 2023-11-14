package sisterhood.hentai.service.ehentai

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class EHentaiRequest(val method: String)

@Serializable
data class EHentaiGalleryRequest(
    @SerialName("gidlist")
    val ids: List<@Serializable(EHentaiGalleryToken.EHentaiGalleryTokenAsListSerializer::class) EHentaiGalleryToken>,
    val namespace: Boolean
) : EHentaiRequest(method = "gdata")

@Serializable
data class EHentaiGalleryResponse(
    @SerialName("gmetadata")
    val galleries: List<EHentaiGallery>
)

@Serializable
data class EHentaiGalleryTokenRequest(
    @SerialName("pagelist")
    val pages: List<@Serializable(EHentaiPageToken.EHentaiPageTokenAsListSerializer::class) EHentaiPageToken>
) : EHentaiRequest(method = "gtoken")

@Serializable
data class EHentaiGalleryTokenResponse(
    @SerialName("tokenlist")
    val tokens: List<EHentaiGalleryToken>
)
