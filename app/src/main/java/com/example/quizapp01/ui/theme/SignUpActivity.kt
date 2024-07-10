package com.example.quizapp01.ui.theme
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.remember
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp01.R
import com.example.quizapp01.ui.theme.ui.login.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


// Initialize Firebase Auth
//private lateinit var auth: FirebaseAuth
// Initialize Firebase Auth
//
//class SignUp :ComponentActivity() {
//
//
//
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        auth = Firebase.auth
//        setContent {
//            QuizApp01Theme {
//                val navController: NavHostController = rememberNavController()
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    // Call the LoginScreen composable
//                    SignUpScreen(navController)
//                }
//            }
//        }
//    }
//}

@Composable
fun SignUpScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val auth = Firebase.auth

    // ...


    Surface(color = Color.White,
        modifier = Modifier.fillMaxSize()
        .background(Color.Black)
    )
             {


        Column(
            modifier = Modifier.padding(23.dp),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            OutlinedTextField(
    textStyle = TextStyle(color = Color.Gray, fontSize = 16.sp),

                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),

                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email Icon")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    autoCorrect = true,
                    imeAction = ImeAction.Next,
                )
            )

            OutlinedTextField(
                textStyle = TextStyle(color = Color.Gray, fontSize = 16.sp),
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Password Icon")
                },
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = "Visibility Toggle"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                ),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation()
            )
            OutlinedTextField(
                textStyle = TextStyle(color = Color.Gray, fontSize = 16.sp),
                value = confirmpassword,
                onValueChange = { confirmpassword = it },
                label = { Text(text = "Confirm Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Password Icon")
                },
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = "Visibility Toggle"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation()
            )
            val context = LocalContext.current
            Button(
                onClick = {
                    createAccount(
                        email,
                        password,
                        confirmpassword,
                        context,
                        navController,
                        auth
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    Electric_Blue
                )
            )
            {
                Text(text = "Sign Up",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = .7.sp
                    )
                )
            }



            TextButton(onClick = {
                navController.navigate(Screen.Login.route)
            }) {
                Text(text = "Already have an account")

            }
        }
    }

}




private const val TAG = "EmailPassword"
 fun createAccount(email: String, password: String,confirmpassword: String,context:Context,navController: NavController,auth: FirebaseAuth) {
    // [START create_user_with_email]

        if (password.isEmpty() || confirmpassword.isEmpty() || email.isEmpty()) {
            Log.w(TAG, "Enter all the blanks")
            Toast.makeText(context,"Enter the credentials", Toast.LENGTH_SHORT).show()
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(password != confirmpassword){
                        Toast.makeText(context,"Password does not match", Toast.LENGTH_SHORT).show()
                    }
                    else if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(context,"Account created", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.Home.route)
//                        val intent = Intent(context, LoginActivity::class.java)
//                        context.startActivity(intent)
//                        (context as Activity).finish()

                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.

                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(context,"Enter correct credentials", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }





