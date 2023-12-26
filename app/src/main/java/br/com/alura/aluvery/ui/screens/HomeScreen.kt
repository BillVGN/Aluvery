package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.ProductsSection
import br.com.alura.aluvery.ui.components.SearchTextField
import br.com.alura.aluvery.ui.theme.AluveryTheme

const val TAG = "HomeScreen"

class HomeScreenUiState(searchText: String = "") {
    var text by mutableStateOf(searchText)

    val searchedProducts
        get() =
            if (text.isNotBlank()) {
                sampleProducts.filter { product ->
                    product.name.contains(text, ignoreCase = true) ||
                            product.description?.contains(text, ignoreCase = true) ?: false
                }
            } else emptyList()

    fun isShowSections(): Boolean {
        return text.isBlank()
    }

}

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    searchText: String = "",
) {
    val state = remember {
        HomeScreenUiState(searchText)
    }
    val searchedProducts = remember(state.text) {
        state.searchedProducts
    }
    Column {
        SearchTextField(
            searchText = state.text,
            onSearchChanged = { newValue ->
                state.text = newValue
            },
            onClearButtonPressed = {
                state.text = ""
            }
        )
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSections()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }

                }
            } else {
                items(searchedProducts) { p ->
                    CardProductItem(product = p, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(sampleSections)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithSearchText() {
    HomeScreen(
        sections = sampleSections,
        "lorem"
    )
}