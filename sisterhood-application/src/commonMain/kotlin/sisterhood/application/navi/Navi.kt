package sisterhood.application.navi

import androidx.compose.runtime.Composable

@Composable
fun Navi(
    startRoute: String = "/",
    naviState: NaviState = rememberNaviState(),
    initialize: NaviScope.() -> Unit = {}
) {
    val scope = NaviScope(naviState)
    scope.initialize()
    scope.naviTo(startRoute)
    scope.render()
}
