package com.example.unidirectionaldataflowcompositionlocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
private fun MainScreen() {
    var height by remember { mutableDoubleStateOf(1.60) }
    var weight by remember { mutableIntStateOf(60) }
    val decimalFormat = DecimalFormat("#.##")
    val imt =
        if (height > 0) (weight / (height * height)) else 0.00
    val imtInterpretation = when {
        imt <= 0.0 -> "Неверные значения"
        imt < 16 -> "Выраженный дефицит массы тела"
        imt in 16.0..18.5 -> "Недостаточная масса тела"
        imt in 18.51..25.0 -> "Нормальная масса тела"
        imt in 25.01..30.0 -> "Избыточная масса тела (предожирение)"
        imt in 30.01..35.0 -> "Ожирение 1-ой степени"
        imt in 35.01..40.0 -> "Ожирение 2-ой степени"
        imt > 40 -> "Ожирение 3-ей степени"
        else -> "Ожирение"
    }
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 45.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
            .background(color = Color.LightGray)
    ) {
        Header()
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.LightGray)
        ) {
            Text(
                text = "-",
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = { height -= 0.05 })
            )
            Text(
                text = decimalFormat.format(height),
                fontSize = 22.sp,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "+",
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = { height += 0.05 })
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.LightGray)
        ) {
            Text(
                text = "-",
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = { weight -= 5 })
            )
            Text(
                text = weight.toString(),
                fontSize = 22.sp,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "+",
                fontSize = 22.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = { weight += 5 })
            )
        }
        ImtResult(decimalFormat, imt, imtInterpretation)
        Text(
            text = "Сбросить",
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable(onClick = {
                    height = 1.60
                    weight = 60
                })
        )
    }
}

@Composable
private fun ImtResult(
    decimalFormat: DecimalFormat,
    imt: Double,
    imtInterpretation: String,
) {
    Text(
        text = decimalFormat.format(imt),
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
    Text(
        text = imtInterpretation,
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun Header() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.DarkGray)
    ) {
        Text(
            text = "Расчет ИМТ",
            fontSize = 28.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}