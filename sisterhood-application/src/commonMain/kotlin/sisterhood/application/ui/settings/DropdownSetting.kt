package sisterhood.application.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownSetting(
    options: List<T>,
    title: String = "",
    label: String = "",
    initiallyExpanded: Boolean = false,
    initialSelectedIndex: Int = 0,
    changeSetting: (T) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }
    var selected by remember { mutableStateOf(options.getOrNull(initialSelectedIndex)?.toString() ?: "") }

    Row(modifier = Modifier.fillMaxWidth().wrapContentSize()) {
        Text(
            text = title,
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
                label = if (label.isNotBlank()) {
                    { Text(text = label) }
                } else null,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach {
                    DropdownMenuItem(
                        text = { Text(it.toString()) },
                        onClick = {
                            changeSetting(it)
                            selected = it.toString()
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
