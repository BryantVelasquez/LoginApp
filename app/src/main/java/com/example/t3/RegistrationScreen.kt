package com.example.t3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(navController: NavController) {
    var firstName by remember { mutableStateOf(TextFieldValue()) }
    var lastName by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var dateOfBirth by remember { mutableStateOf(TextFieldValue()) }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") })
        TextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") })
        TextField(value = dateOfBirth, onValueChange = { dateOfBirth = it }, label = { Text("Date of Birth (YYYY-MM-DD)") })
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        Button(onClick = {
            if (validateInputs(firstName.text, lastName.text, email.text, password.text, dateOfBirth.text)) {
                // Handle successful registration logic
                navController.popBackStack() // Go back to the previous screen
            } else {
                errorMessage = "Please make sure all fields are filled correctly."
            }
        }) {
            Text("Register")
        }
    }
}

fun validateInputs(firstName: String, lastName: String, email: String, password: String, dateOfBirth: String): Boolean {
    return firstName.length in 3..30 && // First name length validation
            lastName.length in 3..30 &&  // Last name length validation
            isValidEmail(email) &&       // Improved email validation
            password.isNotEmpty() &&      // Checks if password is not empty
            dateOfBirth.isNotEmpty()      // Checks if date of birth is not empty
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
