package sisterhood.application.navi

class Route(val route: String) {
    private var parent: Route? = null
    private val children: MutableSet<Route> = mutableSetOf()

    companion object {
        val root = Route("$")
    }

    operator fun div(route: Route): Route {
        if (!this.children.add(route)) {
            throw Exception("Duplicate route")
        }
        route.parent = this
        return route
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Route) {
            return false
        }
        return this.absolute() == other.absolute()
    }

    override fun hashCode(): Int = absolute().hashCode()

    operator fun div(route: String): Route = this / Route(route)

    override fun toString(): String = absolute()

    fun absolute(): String = (parent?.absolute() ?: "") + "/${route}"
}

val Root = Route.root
