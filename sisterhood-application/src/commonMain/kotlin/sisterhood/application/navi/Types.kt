package sisterhood.application.navi

import androidx.compose.runtime.Composable

typealias Screen = @Composable NaviScope.() -> Unit

typealias ScreenWithProp<T> = @Composable NaviScope.(prop: T) -> Unit
