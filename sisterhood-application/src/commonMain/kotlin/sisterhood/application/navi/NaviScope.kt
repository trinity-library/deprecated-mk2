package sisterhood.application.navi

import androidx.compose.runtime.Composable
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

    fun naviTo(route: String) {
        state.currentRoute = route
    }

    @OptIn(InternalSerializationApi::class)
    inline fun <reified T : Any> naviTo(route: String, prop: @Serializable T) {
        naviTo(route)
        state.propSerialized = Json.encodeToString(T::class.serializer(), prop)
    }

    fun naviFrom(route: String, screen: Screen) {
        state.mapping += mapOf(route to screen)
    }

    inline fun <reified T : Any> naviFrom(
        route: String,
        crossinline screenWithProp: ScreenWithProp<@Serializable T>
    ) = naviFrom(route) {
        state.propSerialized?.also { propSerialized ->
            screenWithProp(Json.decodeFromString(propSerialized))
        } ?: run {
            NotFound()
        }
    }

    @Composable
    fun render() {
        state.mapping[state.currentRoute]?.also { currentScreen ->
            currentScreen()
        } ?: run {
            NotFound()
        }
    }
}
