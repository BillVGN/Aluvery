package br.com.alura.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.states.HomeScreenUiState
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.ui.viewmodels.HomeScreenViewModel

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFABClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            }) {
                val viewModel by viewModels<HomeScreenViewModel>()
                HomeScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun App(onFABClick: () -> Unit = {}, content: @Composable () -> Unit = {}) {
    AluveryTheme {
        Surface {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        content = {
                            Icon(Icons.Sharp.Add, contentDescription = null)
                        }, onClick = onFABClick
                    )
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }

        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App {
        HomeScreen(HomeScreenUiState(sampleSections))
    }
}