package com.todokanai.baseproject.compose.presets.dropdownmenu

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyDropdownMenu(
    contents:List<Pair<String,()->Unit>>,
    expanded : Boolean,
    onDismissRequest: () -> Unit,
    modifier:Modifier = Modifier
) {

    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        onDismissRequest = { onDismissRequest() }
    ) {
        contents.forEach {
            DropdownMenuItem(
                text = { Text(it.first) },
                onClick = { it.second() }
            )
        }
    }
}


@Preview
@Composable
private fun DropdownMenuPreview(){
    MyDropdownMenu(
        contents = listOf(Pair("1", {}), Pair("2", {}),Pair("3",{})),
        expanded = true,
        onDismissRequest = {}
    )
}

