package sisterhood.application.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sisterhood.application.usecase.Settings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsComponent(
    settings: Settings,
    changeSettings: (Settings) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(settings.hentaiGridColumns.toString()) }

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Row(modifier = Modifier.fillMaxWidth().wrapContentSize()) {
            Text(
                text = "Hentai Grid Columns",
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.width(140.dp)
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    value = selected,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text(text = "Columns") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    (2..4).forEach { i ->
                        DropdownMenuItem(
                            text = { Text(i.toString()) },
                            onClick = {
                                changeSettings(settings.copy(hentaiGridColumns = i))
                                selected = i.toString()
                                expanded = false
                            },
                            modifier = Modifier.fillMaxHeight(),
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }
    }
}
