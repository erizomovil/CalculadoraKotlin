package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTheme {
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var input1 by remember { mutableStateOf(TextFieldValue("")) }
    var input2 by remember { mutableStateOf(TextFieldValue("")) }
    var error1 by remember { mutableStateOf(false) }
    var error2 by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("") }

    fun validateInput(input: String): Boolean {
        return input.toDoubleOrNull() != null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
        TextField(
            value = input1,
            onValueChange = { input1 = it
                            error1 = !validateInput(it.text)},
            label = { Text("Número 1") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
            if (error1) {
                Text(
                    text = "Por favor, introduce un número válido",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

        TextField(
            value = input2,
            onValueChange = { input2 = it
                            error2 = !validateInput(it.text)},
            label = { Text("Número 2") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
            if (error2) {
                Text(
                    text = "Por favor, introduce un número válido",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (!error1 && !error2) {
            result = try {
                val num1 = input1.text.toDouble()
                val num2 = input2.text.toDouble()
                "Resultado:"+(num1 + num2).toString()
            } catch (e: Exception) {
                "Por Favor, Introduzca solo digitos"
            }
        }else {
                result = "Corrige los errores antes de calcular"
            }
        }
        ) {
            Text("Sumar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("$result", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculadoraTheme {
        CalculatorScreen()
    }
}
