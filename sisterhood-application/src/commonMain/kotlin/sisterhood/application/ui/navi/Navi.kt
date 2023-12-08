package sisterhood.application.ui.navi

import androidx.compose.runtime.Composable

const val DEFAULT_START_ROUTE = "/"

@Composable
fun Navi(
    startRoute: String = DEFAULT_START_ROUTE,
    naviState: NaviState = rememberNaviState(DEFAULT_START_ROUTE),
    initialize: NaviBuilder.() -> Unit = {}
) {
    naviState
        .also {
            NaviBuilder(it).initialize()
        }
        .apply {
            Render(NaviScope(this).naviTo(startRoute))
        }
}
