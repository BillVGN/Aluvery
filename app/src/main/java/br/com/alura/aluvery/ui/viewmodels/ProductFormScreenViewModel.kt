package br.com.alura.aluvery.ui.viewmodels

import android.webkit.URLUtil
import androidx.lifecycle.ViewModel
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.states.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal

class ProductFormScreenViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(
        ProductFormUiState()
    )

    private val dao = ProductDao()

    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUrlValueChange = {
                    _uiState.value = _uiState.value.copy(
                        url = it
                    )
                },
                onNameValueChange = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                },
                onPriceValueChange = {
                    if (it.isBlank() || it.matches(Regex("^[\\d.,]*$"))) {
                        _uiState.value = _uiState.value.copy(
                            price = it
                        )
                    }
                },
                onDescriptionValueChange = {
                    _uiState.value = _uiState.value.copy(
                        description = it
                    )
                }
            )
        }
    }

    fun priceToBigDecimal(price: String) = try {
        BigDecimal(price)
    } catch (e: NumberFormatException) {
        BigDecimal.ZERO
    }

    fun save() {
        _uiState.value.run {
            val product = Product(
                image = url,
                name = name,
                price = priceToBigDecimal(price),
                description = description
            )
            dao.save(product)

        }
    }
}