package sisterhood.application.navi

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.serializersModuleOf
import kotlinx.serialization.serializer

class NaviScope internal constructor(
    state: NaviState
) {
    var state by mutableStateOf(state)

    fun naviTo(route: String) {
        state.currentRoute = route
    }

    @OptIn(InternalSerializationApi::class)
    inline fun <reified T : Any> naviTo(route: String, prop: @Serializable T) {
        naviTo(route)
        state.prop = Json.encodeToString(T::class.serializer(), prop)
    }

    fun naviFrom(route: String, screen: Screen) {
        state.mapping += mapOf(route to screen)
    }

    @Composable
    fun render() {
        if (!state.mapping.contains(state.currentRoute)) {
            Text("Not registered route: `${state.currentRoute}`")
            return
        }
        (state.mapping[state.currentRoute] ?: { NotFound() })()
    }
}
