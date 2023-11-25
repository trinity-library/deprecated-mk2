package sisterhood.application.hentai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import sisterhood.HentaiId
import sisterhood.hentai.service.hitomi.HitomiServiceFactory

internal class HentaiPageStore {
    var state: HentaiPageState by mutableStateOf(HentaiPageState())
        private set

    suspend fun fetchInfo(id: HentaiId) =
        HitomiServiceFactory()
            .create()
            .fetch(id)
            .getOrNull()
            ?.let { HentaiInfo(it.id, it.title, it.language.name) }

    suspend fun fetchThumbnail(id: HentaiId) = ByteArray(0)

    suspend fun onRefresh() {
        state = state.copy(ids = listOf(2726888L))
    }

    data class HentaiPageState(
        val ids: List<HentaiId> = emptyList()
    )
}
