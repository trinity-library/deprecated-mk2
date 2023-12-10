package sisterhood.application.ui.navi

import androidx.compose.runtime.Composable

@Composable
fun Navi(
    initialRoute: String,
    initialize: NaviBuilder.() -> Unit = {}
) {
    rememberNaviState(initialRoute)
        .also { NaviBuilder(it).initialize() }
        .apply { Render(NaviScope(this)) }
}
