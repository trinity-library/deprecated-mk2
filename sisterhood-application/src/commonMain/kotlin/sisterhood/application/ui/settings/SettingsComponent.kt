package sisterhood.application.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sisterhood.application.usecase.Settings

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsComponent(
    settings: Settings,
    changeSettings: (Settings) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(settings.hentaiGridColumns.toString()) }

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Hentai Grid Columns",
                modifier = Modifier.align(Alignment.CenterVertically).wrapContentWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.width(140.dp)
            ) {
                TextField(
                    value = selected,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text(text = "Columns") },
                    trailingIcon = {
                        Icon(
                            imageVector = if (expanded) {
                                Icons.Filled.KeyboardArrowUp
                            } else {
                                Icons.Filled.KeyboardArrowDown
                            },
                            contentDescription = ""
                        )

                    }
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
}
