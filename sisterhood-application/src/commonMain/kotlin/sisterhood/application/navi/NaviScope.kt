package sisterhood.application.navi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class NaviScope internal constructor(
    state: NaviState
) {
    var state by mutableStateOf(state)

    fun naviTo(route: String): NaviScope {
        state.currentProp = null
        state.currentRoute = route
        return this
    }

    @OptIn(InternalSerializationApi::class)
    inline fun <reified T : Any> naviTo(route: String, prop: @Serializable T): NaviScope {
        state.currentProp = Json.encodeToString(T::class.serializer(), prop)
        state.currentRoute = route
        return this
    }
}
