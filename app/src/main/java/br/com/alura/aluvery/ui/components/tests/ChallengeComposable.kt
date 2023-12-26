package br.com.alura.aluvery.ui.components.tests

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.extensions.toBrazilianCurrency
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.ui.theme.Indigo400Light
import coil.compose.AsyncImage
import java.math.BigDecimal

//@Preview(showSystemUi = true)
//@Composable
//fun DesafioLazyGrid() {
//    Column {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            contentPadding = PaddingValues(16.dp),
//            userScrollEnabled = true
//        ) {
//            item(span = {
//                GridItemSpan(maxLineSpan)
//            }) {
//                Text(
//                    text = "Todos os Produtos",
//                    fontSize = 32.sp,
//                )
//            }
//
//            items(sampleProducts) { product ->
//                ProductItem(product = product)
//            }
//        }
//    }
//}

@Composable
fun DesafioCardDescriptionCollapse(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(elevation)
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Indigo400Light)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name
                )
                Text(
                    text = product.price.toBrazilianCurrency()
                )
            }
            product.description?.let {
                Text(
                    text = product.description,
                    maxLines = if (expanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(16.dp)
                        .animateContentSize()
                )
            }
        }
    }
}

@Preview
@Composable
fun DesafioCardDescriptionCollapsePreview() {
    DesafioCardDescriptionCollapse(
        Product(
            name = "Teste",
            price = BigDecimal("9.99"),
            description = LoremIpsum(70).values.first()
        ),
        elevation = 4.dp)
}