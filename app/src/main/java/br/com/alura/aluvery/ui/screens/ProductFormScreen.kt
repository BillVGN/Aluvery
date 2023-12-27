package br.com.alura.aluvery.ui.screens

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import br.com.alura.aluvery.ui.states.ProductFormUiState
import br.com.alura.aluvery.ui.viewmodels.ProductFormScreenViewModel
import coil.compose.AsyncImage

@Composable
fun ProductFormScreen(
    viewModel: ProductFormScreenViewModel,
    onSaveProduct: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    ProductFormScreen(
        state,
        onSaveClick = {
            viewModel.save()
            onSaveProduct()
        }
    )
}

@Composable
fun ProductFormScreen(
    state: ProductFormUiState = ProductFormUiState(),
    onSaveClick: () -> Unit
) {
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
        TextField(
            value = state.price,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            label = {
                Text(text = "Preço")
            },
            prefix = {
                Text(text = "R$ ")
            },
            onValueChange = state.onPriceValueChange
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
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(15)
        ) {
            Text(text = "Salvar")
        }
        Spacer(Modifier)
    }
}