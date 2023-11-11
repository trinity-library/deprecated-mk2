package sisterhood.application.navi

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class NaviScope(private var state: NaviState) {
    fun naviTo(route: String) {
        val newState = NaviState()
        newState.currentRoute = route
        state = newState
    }

    fun naviFrom(route: String, screen: Screen) {
        state.registerRoute(route, screen)
    }

    @Composable
    fun render() {
        if (state.currentScreen == null) {
            Text("Not registered route: `${state.currentRoute}`")
            return
        }
        state.currentScreen!!(state)
    }
}
