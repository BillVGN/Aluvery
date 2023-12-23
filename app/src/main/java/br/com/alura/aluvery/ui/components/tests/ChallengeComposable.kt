package br.com.alura.aluvery.ui.components.tests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.ui.components.ProductItem

@Preview(showSystemUi = true)
@Composable
fun DesafioLazyGrid() {
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
            userScrollEnabled = true
        ) {
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Text(
                    text = "Todos os Produtos",
                    fontSize = 32.sp,
                )
            }

            items(sampleProducts) { product ->
                ProductItem(product = product)
            }
        }
    }
}