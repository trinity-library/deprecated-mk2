package sisterhood.application.hentai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import sisterhood.hentai.service.KtorHentaiServiceFactory

internal class HentaiPageStore {
    var state: HentaiPageState by mutableStateOf(HentaiPageState())
        private set

    suspend fun fetchInfo(id: Int) =
        KtorHentaiServiceFactory()
            .create()
            .fetch(id)
            .getOrNull()
            ?.let { HentaiInfo(it.id, it.title, it.language.name) }

    suspend fun fetchThumbnail(id: Int) = ByteArray(0)

    suspend fun onRefresh() {
        state = state.copy(ids = listOf(2726888))
    }

    data class HentaiPageState(
        val ids: List<Int> = emptyList()
    )
}
