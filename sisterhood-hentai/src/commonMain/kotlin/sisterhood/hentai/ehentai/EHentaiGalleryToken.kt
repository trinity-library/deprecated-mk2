package sisterhood.hentai.ehentai

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeCollection

@Serializable
data class EHentaiGalleryToken(
    val gid: Int,
    val token: String
) {
    object EHentaiGalleryTokenAsListSerializer : KSerializer<EHentaiGalleryToken> {
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor
            get() = listSerialDescriptor<String>()

        override fun deserialize(decoder: Decoder): EHentaiGalleryToken =
            decoder.decodeStructure(descriptor) {
                EHentaiGalleryToken(decodeIntElement(descriptor, 0), decodeStringElement(descriptor, 1))
            }

        override fun serialize(encoder: Encoder, value: EHentaiGalleryToken) =
            encoder.encodeCollection(descriptor, 2) {
                encodeStringElement(descriptor, 0, value.gid.toString())
                encodeStringElement(descriptor, 1, value.token)
            }
    }
}
