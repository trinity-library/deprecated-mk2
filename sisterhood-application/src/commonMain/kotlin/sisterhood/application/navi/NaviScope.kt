package sisterhood.application.navi

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class NaviScope internal constructor(
    state: NaviState
) {
    var state by mutableStateOf(state)

    fun naviTo(route: String) {
        state.currentRoute = route
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
        state.mapping[state.currentRoute]!!(state.currentRoute)
    }
}
