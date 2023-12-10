package sisterhood.application.ui.navi

import kotlinx.serialization.Serializable

class NaviBuilder internal constructor(
    val state: NaviState
) {
    fun naviFrom(route: String, screen: Screen) {
        state.register(route, screen)
    }

    inline fun <reified T : Any> naviFrom(
        route: String,
        crossinline screenWithProp: ScreenWithProp<@Serializable T>
    ) {
        state.register(route, screenWithProp)
    }
}
