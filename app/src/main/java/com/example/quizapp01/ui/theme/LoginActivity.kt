package com.example.quizapp01.ui.theme

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp01.R
import com.example.quizapp01.ui.theme.ui.login.UserProfileScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignIn.*
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth

class LoginActivity : ComponentActivity() {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        val gso = Builder(DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()


        mGoogleSignInClient = getClient(this, gso)

        setContent {
            QuizApp01Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                   val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(navController=navController,mGoogleSignInClient)
                        }
                        composable("home") {
                            ScaffoldExample(navController=navController)
                        }
                        composable("signup") {
                            SignUpScreen(navController=navController)
                        }
                        composable("userdata"){
                            UserProfileScreen(navController=navController)
                        }


                    }
                }
            }
        }
    }
}


@Composable
fun LoginScreen(navController: NavController,mGoogleSignInClient: GoogleSignInClient) {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = getSignedInAccountFromIntent(result.data)
                task.addOnCompleteListener { signInTask ->
                    if (signInTask.isSuccessful) {
                        try {
                            val account = signInTask.getResult(ApiException::class.java)!!
                            val idToken = account.idToken
                            val credential = GoogleAuthProvider.getCredential(idToken, null)
                            Firebase.auth.signInWithCredential(credential)
                                .addOnCompleteListener { authTask ->
                                    if (authTask.isSuccessful) {
                                        Toast.makeText(context, "Authentication Successful", Toast.LENGTH_SHORT).show()
                                        navController.navigate("userdata")
                                    } else {
                                        Toast.makeText(context, "Authentication Failed: ${authTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        } catch (e: ApiException) {
                            Toast.makeText(context, "Google Sign-In Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Google Sign-In Task Failed: ${signInTask.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    )
    Column(
        modifier = Modifier.padding(23.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
               OutlinedTextField(    //....................Email..........................//
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

        OutlinedTextField(  //....................Password..........................//
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
                imeAction = ImeAction.Done,
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        )

        Button(  //....................Login Button..........................//
            onClick = { signIn(email, password, navController) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                Electric_Blue
            )
        ) {
            Text(text = "Login")
        }

//................................................Login Ends....................................//
        Row {   //..............Navigation to signup and password reset .......................//
            TextButton(onClick = {
                navController.navigate("signup")
            }) {
                Text(text = "Don't have an account?")
            }

            TextButton(onClick = {
                if (email.isNotEmpty()) {
                    auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Password reset email sent!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Error sending password reset email", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "Forgot Password?")
            }
        }


        Spacer(modifier = Modifier.padding(8.dp))
        Canvas(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
        ) {
            drawLine(
                color = Color.Black,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f)
            )
        }



        Box(   //..................Google Sign In ...........................................//
            Modifier.clickable {
                signInLauncher.launch(mGoogleSignInClient.signInIntent)
            }
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(2.dp))

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.Companion.vectorResource(id = R.drawable.google_icon),
                        contentDescription = "Google Icon",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(60.dp)
                    )

                    Text(
                        text = "Sign In with Google", modifier = Modifier.padding(10.dp, 0.dp),
                        color = Purple40, fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
    //............Login Screen End.............................................//


private const val TAG = "EmailPassword"
fun signIn(email: String, password: String, navController: NavController) {
    if (email.isEmpty() || password.isEmpty()) {
        Log.d(TAG, "signInWithEmail:null")
        Toast.makeText(navController.context, "Enter the credentials", Toast.LENGTH_SHORT).show()
    } else {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(navController.context, "Logging in", Toast.LENGTH_SHORT).show()
                    navController.navigate("userdata")
                    updateUI(auth.currentUser)
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(navController.context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }
}


fun updateUI(user: FirebaseUser?) {

}






