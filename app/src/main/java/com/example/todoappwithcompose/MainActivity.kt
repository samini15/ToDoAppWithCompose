package com.example.todoappwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoappwithcompose.navigation.SetUpNavigation
import com.example.todoappwithcompose.ui.theme.ToDoAppWithComposeTheme
import com.example.todoappwithcompose.viewModel.ToDoSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private val sharedViewModel: ToDoSharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppWithComposeTheme {
                navController = rememberNavController()
                SetUpNavigation(navController = navController, sharedViewModel = sharedViewModel)
            }
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoAppWithComposeTheme {
        Greeting("Android")
    }
}*/