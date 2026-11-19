package com.example.navigationwitharguments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.navigationwitharguments.ui.theme.NavigationWithArgumentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationWithArgumentsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        // Halaman pertama
        composable<Home> {
            HomeScreen(
                onNavigateToDetails = { productName, quantity ->

                    navController.navigate(
                        OrderDetails(productName, quantity)
                    )
                }
            )
        }

        // Halaman kedua
        composable<OrderDetails> {

            val args = it.toRoute<OrderDetails>()

            OrderDetailsScreen(
                navController = navController,
                productName = args.productName,
                quantity = args.quantity
            )
        }
    }
}
