package br.com.alura.aluvery.ui.screens

import android.icu.text.DecimalFormatSymbols
import android.webkit.URLUtil
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.components.DecimalInputField
import br.com.alura.aluvery.utilities.DecimalFormatter
import coil.compose.AsyncImage
import java.math.BigDecimal
import java.util.Locale

class ProductFormScreenUiState(
    val onSaveClick: () -> Unit = {},
    val onUrlValueChange: (String) -> Unit = {},
    val onNameValueChange: (String) -> Unit = {},
    val onPriceValueChange: (String) -> Unit = {},
    val onDescriptionValueChange: (String) -> Unit = {},
    val decimalFormatter: DecimalFormatter = DecimalFormatter(DecimalFormatSymbols.getInstance()),
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val isURLValid: Boolean = url.isNotBlank() && URLUtil.isNetworkUrl(url)
)

@Composable
fun ProductFormScreen(onSaveProduct: (Product) -> Unit) {
    var url: String by rememberSaveable { mutableStateOf("") }
    var name: String by rememberSaveable { mutableStateOf("") }
    var price: String by rememberSaveable { mutableStateOf("") }
    var description: String by rememberSaveable { mutableStateOf("") }

    val decimalFormatter = DecimalFormatter(symbols = DecimalFormatSymbols(Locale("pt", "br")))

    val priceToBigDecimal = try {
        BigDecimal(price)
    } catch (e: NumberFormatException) {
        BigDecimal.ZERO
    }

    val state =
        ProductFormScreenUiState(
            url = url,
            name = name,
            price = price,
            description = description,
            decimalFormatter = decimalFormatter,
            onSaveClick = {
                val product = Product(
                    image = url,
                    name = name,
                    price = priceToBigDecimal,
                    description = description
                )
                onSaveProduct(product)
            },
            onUrlValueChange = {
                url = it
            },
            onNameValueChange = {
                name = it
            },
            onPriceValueChange = {
                price = decimalFormatter.cleanup(it)
            },
            onDescriptionValueChange = {
                description = it
            }
        )

    ProductFormScreen(state)
}

@Composable
fun ProductFormScreen(state: ProductFormScreenUiState = ProductFormScreenUiState()) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        Spacer(Modifier)
        Text(
            text = "Criando o produto",
            fontSize = 28.sp,
            modifier = Modifier.fillMaxWidth(),
        )
        if (state.isURLValid) {
            AsyncImage(
                model = state.url,
                contentDescription = null,
                modifier = Modifier
                    .heightIn(min = 100.dp, max = 300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }
        TextField(
            value = state.url,
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            ),
            label = { Text(text = "Url da Imagem") },
            onValueChange = state.onUrlValueChange
        )
        TextField(
            value = state.name,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Nome") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
            onValueChange = state.onNameValueChange
        )

        DecimalInputField(
            value = state.price,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            label = { Text(text = "Preço") },
            prefix = {
                Text(text = "R$ ")
            },
            decimalFormatter = state.decimalFormatter,
            onValueChanged = state.onPriceValueChange
        )
        TextField(
            value = state.description,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            label = { Text(text = "Descrição") },
            onValueChange = state.onDescriptionValueChange
        )
        Button(
            onClick = state.onSaveClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15)
        ) {
            Text(text = "Salvar")
        }
        Spacer(Modifier)
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductFormScreenPreview() {
    ProductFormScreen()
}