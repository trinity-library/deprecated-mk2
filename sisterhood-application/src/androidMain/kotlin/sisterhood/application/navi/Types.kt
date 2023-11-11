package sisterhood.application.navi

import androidx.compose.runtime.Composable

typealias Screen = @Composable NaviScope.(NaviState) -> Unit
