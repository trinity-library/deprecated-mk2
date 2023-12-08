package sisterhood.application.ui.navi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class NaviBuilder internal constructor(
    state: NaviState
) {
    var state by mutableStateOf(state)

    fun naviFrom(route: String, screen: Screen) {
        state.register(route, screen)
    }

    inline fun <reified T : Any> naviFrom(
        route: String,
        crossinline screenWithProp: ScreenWithProp<@Serializable T>
    ) = naviFrom(route) {
        state.currentProp?.also { serializedProp ->
            screenWithProp(Json.decodeFromString(serializedProp))
        } ?: run {
            NotFound()
        }
    }
}
