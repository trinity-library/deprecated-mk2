package sisterhood.application.ui.navi

import kotlinx.serialization.Serializable

class NaviScope internal constructor(
    val state: NaviState
) {
    fun naviBack(): NaviScope {
        state.naviBack()
        return this
    }

    fun naviTo(route: String): NaviScope {
        state.naviTo(route)
        return this
    }

    inline fun <reified T : Any> naviTo(route: String, prop: @Serializable T): NaviScope {
        state.naviTo(route, prop)
        return this
    }
}
