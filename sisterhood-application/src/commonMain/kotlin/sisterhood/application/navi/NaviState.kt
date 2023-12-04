package sisterhood.application.navi

import androidx.compose.runtime.*

@Composable
fun rememberNaviState(
    currentRoute: String = "/",
    stack: ArrayDeque<String> = arrayDequeOf(),
    mapping: Map<String, Screen> = emptyMap(),
    propSerialized: String? = null
) = remember {
    NaviState(currentRoute, stack, mapping, propSerialized)
}

@Stable
class NaviState(
    currentRoute: String,
    stack: ArrayDeque<String>,
    mapping: Map<String, Screen>,
    propSerialized: String?
) {
    var currentRoute by mutableStateOf(currentRoute)
    var stack by mutableStateOf(stack)
    var mapping by mutableStateOf(mapping)
    var propSerialized by mutableStateOf(propSerialized)
}
