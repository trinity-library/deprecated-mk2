package sisterhood.application.ui.navi

import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

@Composable
fun rememberNaviState(
    initialRoute: Route = "/"
) = remember {
    NaviState(initialRoute, null)
}

@Stable
class NaviState(
    initialRoute: Route,
    initialProp: SerializedProp?
) {
    private val screens: MutableMap<Route, Screen> = mutableMapOf()

    val stack: ArrayDeque<Pair<Route, SerializedProp?>> = arrayDequeOf()

    var currentProp: SerializedProp? = initialProp
    var currentRoute by mutableStateOf(initialRoute)

    fun register(route: Route, screen: Screen) {
        screens[route] = screen
    }

    inline fun <reified T : Any> register(
        route: String,
        crossinline screenWithProp: ScreenWithProp<@Serializable T>
    ) = register(route) {
        currentProp?.also { serializedProp ->
            screenWithProp(Json.decodeFromString(serializedProp))
        } ?: run {
            NotFound()
        }
    }

    fun naviBack() {
        stack.pop()?.let { (route, prop) ->
            currentProp = prop
            currentRoute = route
        }
    }

    fun naviTo(route: String) {
        stack.push(currentRoute to currentProp)
        currentProp = null
        currentRoute = route
    }

    @OptIn(InternalSerializationApi::class)
    inline fun <reified T : Any> naviTo(route: String, prop: @Serializable T) {
        stack.push(currentRoute to currentProp)
        currentProp = Json.encodeToString(T::class.serializer(), prop)
        currentRoute = route
    }

    @Composable
    fun Render(scope: NaviScope) {
        screens.forEach { (route, screen) ->
            AnimatedVisibility(
                visible = (route == currentRoute),
                modifier = Modifier,
                enter = slideInVertically() + expandVertically() + fadeIn(),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                scope.screen()
            }
        }
    }
}
