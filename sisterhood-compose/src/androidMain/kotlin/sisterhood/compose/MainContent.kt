package sisterhood.compose

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent() {
    Scaffold {
        Text("Hello, world!")
    }
}
