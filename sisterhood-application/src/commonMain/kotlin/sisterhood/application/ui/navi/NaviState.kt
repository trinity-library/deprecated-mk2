package sisterhood.application.ui.navi

import androidx.compose.runtime.*

@Composable
fun rememberNaviState(
    startRoute: Route = "/"
) = remember {
    NaviState(startRoute, null)
}

@Composable
fun rememberNaviState(
    startRoute: Route,
    prop: SerializedProp
) = remember {
    NaviState(startRoute, prop)
}

@Stable
class NaviState(
    startRoute: Route,
    prop: SerializedProp?
) {
    private val screens: MutableMap<Route, Screen> = mutableMapOf()

    var currentProp by mutableStateOf(prop)
    var currentRoute by mutableStateOf(startRoute)

    fun register(route: Route, screen: Screen) {
        screens[route] = screen
    }

    @Composable
    fun Render(scope: NaviScope) {
        screens[currentRoute]?.also { currentScreen ->
            scope.currentScreen()
        } ?: run {
            NotFound()
        }
    }
}
