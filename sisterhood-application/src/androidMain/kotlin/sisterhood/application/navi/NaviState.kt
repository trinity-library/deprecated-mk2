package sisterhood.application.navi

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class NaviState {
    private val mapping: MutableMap<String, Screen> = mutableMapOf()
    private val stack: ArrayDeque<String> = arrayDequeOf()

    var currentRoute: String
        get() = stack.peek()!!
        set(route) = stack.push(route)

    val currentScreen: Screen?
        get() = mapping[currentRoute]

    fun registerRoute(route: String, screen: Screen) {
        mapping[route] = screen
    }
}
