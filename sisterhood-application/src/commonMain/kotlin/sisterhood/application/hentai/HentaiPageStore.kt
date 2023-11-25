package sisterhood.application.hentai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import sisterhood.HentaiId
import sisterhood.HentaiLanguage
import sisterhood.hentai.service.hitomi.HitomiServiceFactory

internal class HentaiPageStore {
    var state: HentaiPageState by mutableStateOf(HentaiPageState())
        private set

    suspend fun fetchInfo(id: HentaiId) =
        HitomiServiceFactory()
            .create()
            .fetchHentai(id)
            .getOrNull()
            ?.let { HentaiInfo(it.id, it.title, it.language.name) }

    suspend fun fetchThumbnail(id: HentaiId) =
        HitomiServiceFactory()
            .create()
            .fetchThumbnail(id)
            .getOrDefault(ByteArray(0))

    suspend fun onRefresh() =
        HitomiServiceFactory()
            .create()
            .fetchIds(HentaiLanguage.KOREAN, 0, 10)
            .getOrDefault(state.ids)
            .let { state = state.copy(ids = it) }

    data class HentaiPageState(
        val ids: List<HentaiId> = emptyList()
    )
}
