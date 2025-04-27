package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   UnitConverter()
                }
                }
            }
        }
    }

val customTextStyle = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 32.sp,
    color = Color.Red
)

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue  by remember { mutableStateOf("") }
    var inputUnite by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false ) }
    var oExpended by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00) }

    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.doubleValue * 100.0 / oConversionFactor.doubleValue).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Unit Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnit()
            },
            label = { Text("Enter Value")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // Input Box
            Box {
                // Input Button
                Button(
                    onClick = {iExpanded = true}
                ) {
                    Text(text = inputUnite)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
               DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                   DropdownMenuItem(
                       text = {Text("Centimeters")},
                       onClick = {
                           iExpanded = false
                           inputUnite = "Centimeters"
                           conversionFactor.doubleValue = 0.01
                           convertUnit()
                       }
                   )
                   DropdownMenuItem(
                       text = {Text("Meters")},
                       onClick = {
                           iExpanded = false
                           inputUnite = "Meters"
                           conversionFactor.doubleValue = 1.0
                           convertUnit()
                       }
                   )
                   DropdownMenuItem(
                       text = {Text("Feet")},
                       onClick = {
                           iExpanded = false
                           inputUnite = "Feet"
                           conversionFactor.doubleValue = 0.3048
                           convertUnit()
                       }
                   )
                   DropdownMenuItem(
                       text = {Text("Millimeters")},
                       onClick = {
                           iExpanded = false
                           inputUnite = "Millimeters"
                           conversionFactor.doubleValue = 0.001
                           convertUnit()
                       }
                   )
               }
            }
            Spacer(modifier =  Modifier.width(16.dp))
            // Output Box
            Box {
                // Output Button
                Button(
                    onClick = {oExpended = true}
                ) {
                    Text(text =  outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpended, onDismissRequest = {oExpended = false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            oExpended = false
                            outputUnit = "Centimeters"
                            oConversionFactor.doubleValue = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            oExpended = false
                            outputUnit = "Meters"
                            oConversionFactor.doubleValue = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            oExpended = false
                            outputUnit = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            oExpended = false
                            outputUnit = "Millimeters"
                            oConversionFactor.doubleValue = 0.001
                            convertUnit()
                        }
                    )
                }
            }
        }
        Spacer(modifier =  Modifier.height(16.dp))
        // Result Text
        Text(
            text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
        UnitConverter()
}


