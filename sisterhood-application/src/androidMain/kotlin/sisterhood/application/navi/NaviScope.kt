package sisterhood.application.navi

class NaviScope(private val state: NaviState) {
    fun naviTo(route: Route) {
        state.currentRoute = route
    }
}
