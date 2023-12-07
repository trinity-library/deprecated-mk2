package sisterhood.application.hentai

import androidx.compose.runtime.Composable
import sisterhood.usecase.HentaiInfo

@Composable
fun HentaiComponent(
    hentaiGridState: HentaiGridState = rememberHentaiGridState(),
    onPressItem: (HentaiInfo) -> Unit = {},
) {
    HentaiGrid(
        onPressItem = onPressItem,
        state = hentaiGridState
    )
}
