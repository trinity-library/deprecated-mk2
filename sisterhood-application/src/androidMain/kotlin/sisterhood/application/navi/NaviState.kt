package sisterhood.application.navi

class NaviState {
    private val stack = arrayDequeOf(Root)

    var currentRoute: Route
        get() = stack.peek()!!
        set(route) = stack.push(route)
}
