package sisterhood.hentai.ehentai

import kotlinx.serialization.Serializable

@Serializable
sealed class EHentaiRequest(val method: String)

@Serializable
data class EHentaiGalleryRequest(
    val gidlist: List<@Serializable(EHentaiGalleryToken.EHentaiGalleryTokenAsListSerializer::class) EHentaiGalleryToken>,
    val namespace: Boolean
) : EHentaiRequest(method = "gdata")

@Serializable
data class EHentaiGalleryResponse(
    val gmetadata: List<EHentaiGallery>
)

@Serializable
data class EHentaiGalleryTokenRequest(
    val pagelist: List<@Serializable(EHentaiPageToken.EHentaiPageTokenAsListSerializer::class) EHentaiPageToken>
) : EHentaiRequest(method = "gtoken")

@Serializable
data class EHentaiGalleryTokenResponse(
    val tokenlist: List<EHentaiGalleryToken>
)
