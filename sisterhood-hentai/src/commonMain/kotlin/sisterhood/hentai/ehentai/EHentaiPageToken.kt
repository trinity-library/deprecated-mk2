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
data class EHentaiPageToken(
    val gid: Long,
    val token: String,
    val page: Int
) {
    object EHentaiPageTokenAsListSerializer : KSerializer<EHentaiPageToken> {
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor
            get() = listSerialDescriptor<String>()

        override fun deserialize(decoder: Decoder): EHentaiPageToken =
            decoder.decodeStructure(descriptor) {
                EHentaiPageToken(
                    decodeLongElement(descriptor, 0),
                    decodeStringElement(descriptor, 1),
                    decodeIntElement(descriptor, 2)
                )
            }

        override fun serialize(encoder: Encoder, value: EHentaiPageToken) =
            encoder.encodeCollection(descriptor, 2) {
                encodeStringElement(descriptor, 0, value.gid.toString())
                encodeStringElement(descriptor, 1, value.token)
                encodeStringElement(descriptor, 2, value.page.toString())
            }
    }
}
