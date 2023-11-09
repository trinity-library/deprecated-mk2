package sisterhood.application.navi

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Navi(
    startRoute: Route? = null,
    naviState: NaviState = NaviState(),
    routeToScreen: @Composable NaviScope.(Route) -> Unit = {}
) {
    if (startRoute != null) {
        naviState.currentRoute = startRoute
    }
    NaviScope(naviState).routeToScreen(naviState.currentRoute)
}
