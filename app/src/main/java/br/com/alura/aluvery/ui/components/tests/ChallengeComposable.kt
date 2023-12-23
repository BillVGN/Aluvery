package br.com.alura.aluvery.ui.components.tests

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.R

//@Preview
@Composable
fun Desafio1() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .width(100.dp)
                .background(Color.Blue)
        )
        Column {
            Text(
                "Test 1",
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFededed))
                    .padding(16.dp)
            )
            Text("Test 2", Modifier.padding(16.dp))
        }
    }
}

//@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun Desafio2() {
    val colorStart = Color(0xFF3700B3)
    val colorEnd = Color(0xFFBB86FC)
    Surface(
        modifier = Modifier.padding(10.dp),
        shape = RoundedCornerShape(6.dp),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .width(300.dp)
                .height(150.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                colorStart, colorEnd
                            )
                        )
                    )
                    .fillMaxHeight()
                    .width(80.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .size(78.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = 39.dp)
                        .clip(CircleShape)
                        .border(
                            2.dp, Brush.verticalGradient(
                                listOf(
                                    colorEnd,
                                    colorStart,
                                )
                            ), CircleShape
                        )
                )
            }
            Spacer(modifier = Modifier.size(39.dp))
            Text(
                text = LoremIpsum(50).values.first(),
                fontSize = 12.sp,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Composable
fun ProductsSectionDesafio3() {
    Column {
        Text(
            text = "Promoções",
            Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        Row(
            Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp), // espaçamento no início e fim
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductItemDesafio3(LoremIpsum(50).values.first())
            ProductItemDesafio3("")
            ProductItemDesafio3(LoremIpsum(10).values.first())
        }
    }
}

@Composable
fun ProductItemDesafio3(description: String) {
    Surface(shape = RoundedCornerShape(10.dp), tonalElevation = 4.dp, shadowElevation = 4.dp) {
        Column(
            Modifier
                .heightIn(250.dp, 260.dp)
                .width(200.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val imageSize = 100.dp
            Box(
                modifier = Modifier
                    .height(imageSize)
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                Color(0xFF6200EE), Color(0xFF03DAC5)
                            )
                        )
                    )
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    Modifier
                        .size(imageSize)
                        .offset(y = imageSize / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter)
                        .border(
                            BorderStroke(1.dp, Color.White), RoundedCornerShape(CornerSize(10.dp))
                        )
                )
            }
            Column(Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(imageSize / 2))
                Text(
                    text = LoremIpsum(50).values.first(),
                    fontWeight = FontWeight(700),
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "R$ 14,99",
                    Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            }
            if (description.isNotBlank()) {
                Text(
                    text = description,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color(0xFF6200EE))
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ProductsSectionDesafio3Preview() {
    ProductsSectionDesafio3()
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ProductItemDesafio3Preview() {
    ProductItemDesafio3(LoremIpsum(50).values.first())
}