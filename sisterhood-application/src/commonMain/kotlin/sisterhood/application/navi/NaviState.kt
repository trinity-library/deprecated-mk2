package sisterhood.application.navi

import androidx.compose.runtime.*

@Composable
fun rememberNaviState(
    currentRoute: String = "/",
    stack: ArrayDeque<String> = arrayDequeOf(),
    mapping: Map<String, Screen> = emptyMap()
) =
    remember {
        NaviState(currentRoute, stack, mapping)
    }

@Stable
class NaviState(
    currentRoute: String,
    stack: ArrayDeque<String>,
    mapping: Map<String, Screen>,
) {
    var currentRoute by mutableStateOf(currentRoute)
    var stack by mutableStateOf(stack)
    var mapping by mutableStateOf(mapping)
}
