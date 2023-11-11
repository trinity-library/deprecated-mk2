package sisterhood.application.navi

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Navi(
    startRoute: String,
    naviState: NaviState = NaviState(),
    initialize: NaviScope.(String) -> Unit = {}
) {
    naviState.currentRoute = startRoute

    val scope = NaviScope(naviState)
    scope.initialize(naviState.currentRoute)
    scope.render()
}
