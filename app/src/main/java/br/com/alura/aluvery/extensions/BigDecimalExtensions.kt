package br.com.alura.aluvery.extensions

import android.icu.text.NumberFormat
import java.math.BigDecimal
import java.util.Locale

fun BigDecimal.toBrazilianReais(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
    return numberFormat.format(this).toString()
}