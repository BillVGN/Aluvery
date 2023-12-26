package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import br.com.alura.aluvery.utilities.DecimalFormatter
import br.com.alura.aluvery.utilities.DecimalInputVisualTransformation

@Composable
fun DecimalInputField(
    modifier: Modifier = Modifier,
    value: String,
    label: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    decimalFormatter: DecimalFormatter,
    onValueChanged: (newValue: String) -> Unit,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
) {
    TextField(
        modifier = modifier,
        value = value,
        label = label,
        prefix = prefix,
        suffix = suffix,
        onValueChange = onValueChanged,
        keyboardOptions = keyboardOptions,
        visualTransformation = DecimalInputVisualTransformation(decimalFormatter)
    )
}
