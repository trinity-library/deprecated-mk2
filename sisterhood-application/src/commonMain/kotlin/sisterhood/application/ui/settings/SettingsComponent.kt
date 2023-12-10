package sisterhood.application.ui.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import sisterhood.application.usecase.Settings

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun SettingsComponent(
    settings: Settings,
    changeSettings: (Settings) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(settings.hentaiGridColumns.toString()) }

    Row {
        Text(text = "Hentai Grid Columns")

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            TextField(
                value = selected,
                onValueChange = { },
                enabled = false
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                for (i in 2..4) {
                    DropdownMenuItem(
                        onClick = {
                            changeSettings(settings.copy(hentaiGridColumns = i))
                            selected = i.toString()
                            expanded = false
                        }
                    ) {
                        Text(i.toString())
                    }
                }
            }
        }
    }
}
