package br.com.alura.aluvery.ui.states

data class ProductFormUiState(
    val onUrlValueChange: (String) -> Unit = {},
    val onNameValueChange: (String) -> Unit = {},
    val onPriceValueChange: (String) -> Unit = {},
    val onDescriptionValueChange: (String) -> Unit = {},
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = ""
) {
    val isURLValid: Boolean get() = url.isNotBlank()
}