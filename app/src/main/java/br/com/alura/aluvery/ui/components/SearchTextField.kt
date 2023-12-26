package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(
    searchText: String,
    onSearchChanged: (String) -> Unit,
    onClearButtonPressed: () -> Unit
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue ->
            onSearchChanged(newValue)
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Procurar"
            )
        },
        label = {
            Text(text = "Produto")
        },
        placeholder = {
            Text(text = "O que você procura?")
        },
        /* Início código firula */
        trailingIcon = {
            if (searchText.isNotBlank()) {
                FilledIconButton(
                    onClick = onClearButtonPressed,
                    content = {
                        Icon(
                            imageVector = Icons.Sharp.Clear,
                            contentDescription = "Limpar campo"
                        )
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Transparent),
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        /* Fim código firula */
        shape = RoundedCornerShape(100),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}