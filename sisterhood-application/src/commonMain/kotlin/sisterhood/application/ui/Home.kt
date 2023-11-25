package sisterhood.application.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Home() {
    Surface(Modifier.fillMaxSize()) {
        Column {
            TopAppBar(
                title = {
                    Row {
                        Text(
                            text = "Sisterhood"
                        )
                    }
                }
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items((0..10).toList()) { idx ->
                    Row {
                        AsyncImage(
                            load = { Icons.Default.Home },
                            painterFor = { rememberVectorPainter(it) }
                        )

                        Text(
                            text = "id: $idx"
                        )
                    }
                }
            }
        }
    }
}
