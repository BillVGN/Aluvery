package br.com.alura.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import br.com.alura.aluvery.ui.screens.ProductFormScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.ui.viewmodels.ProductFormScreenViewModel

const val TAG = "ProductFormActivity"

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    val viewModel: ProductFormScreenViewModel by viewModels()
                    ProductFormScreen(
                        viewModel,
                        onSaveProduct = {
                            finish()
                        })
                }
            }
        }
    }
}
