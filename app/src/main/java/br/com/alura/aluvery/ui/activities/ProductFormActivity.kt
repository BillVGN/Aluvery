package br.com.alura.aluvery.ui.activities

import android.icu.text.DecimalFormatSymbols
import android.os.Bundle
import android.util.Log
import android.webkit.URLUtil
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.components.DecimalInputField
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.utilities.DecimalFormatter
import coil.compose.AsyncImage
import java.math.BigDecimal
import java.util.Locale

const val TAG = "ProductFormActivity"

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen(onSaveClick = {product ->
                        dao.save(product)
                        finish()
                    })
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen(onSaveClick: (Product) -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        var url: String by remember { mutableStateOf("") }
        var name: String by remember { mutableStateOf("") }
        var price: String by remember { mutableStateOf("") }
        var description: String by remember { mutableStateOf("") }
        Spacer(Modifier)
        Text(
            text = "Criando o produto",
            fontSize = 28.sp,
            modifier = Modifier.fillMaxWidth(),
        )
        if (url.isNotBlank() && URLUtil.isNetworkUrl(url)) {
            AsyncImage(
                model = url,
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
            value = url,
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            ),
            label = { Text(text = "Url da Imagem") },
            onValueChange = {
                url = it
            })
        TextField(
            value = name,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Nome") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
            onValueChange = {
                name = it
            })
//        TextField(
//            value = price,
//            modifier = Modifier.fillMaxWidth(),
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Decimal,
//                imeAction = ImeAction.Next
//            ),
//            label = { Text(text = "Preço") },
//            onValueChange = { newValue ->
//                price = newValue
//            })
        val decimalFormatter = DecimalFormatter(symbols = DecimalFormatSymbols(Locale("pt", "br")))
        DecimalInputField(
            value = price,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            label = { Text(text = "Preço") },
            prefix = {
                Text(text = "R$ ")
            },
            decimalFormatter = decimalFormatter,
            onValueChanged = {
                price = decimalFormatter.cleanup(it)
            }
        )
        TextField(
            value = description,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            ),
            label = { Text(text = "Descrição") },
            onValueChange = {
                description = it
            })
        Button(
            onClick = {
                val product = Product(
                    name = name,
                    image = url,
                    price = try {
                        BigDecimal(price)
                    } catch (e: NumberFormatException) {
                        BigDecimal.ZERO
                    },
                    description = description
                )
                Log.i(TAG, "ProductFormScreen: $product")
                onSaveClick(product)
            },
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