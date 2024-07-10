package com.appenginex.com.testmmdemo.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.appenginex.com.common.Constants.EMPTY_TEXT
import com.appenginex.com.common.navigation.NavigationRoutes
import com.appenginex.com.common.navigation.Screen
import com.appenginex.com.creditcard.info.InfoCardScreen
import com.appenginex.com.dash.DashboardScreen
import com.appenginex.com.model.CreditCard

//import com.appenginex.com.transaction.TransactionsScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController)
        }
        composable(
            route = NavigationRoutes.InfoCreditCard.INFO_CREDIT_CARD,
            arguments = listOf(
                navArgument(NavigationRoutes.InfoCreditCard.argCardHolderName) { type = NavType.StringType },
                navArgument(NavigationRoutes.InfoCreditCard.argCardNumber) { type = NavType.StringType },
                navArgument(NavigationRoutes.InfoCreditCard.argExpiryDate) { type = NavType.StringType },
                navArgument(NavigationRoutes.InfoCreditCard.argCvv) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val creditCard = CreditCard(
                cardHolderName = backStackEntry.arguments?.getString(NavigationRoutes.InfoCreditCard.argCardHolderName) ?: EMPTY_TEXT,
                cardNumber = backStackEntry.arguments?.getString(NavigationRoutes.InfoCreditCard.argCardNumber)?: EMPTY_TEXT,
                expiryDate = backStackEntry.arguments?.getString(NavigationRoutes.InfoCreditCard.argCardNumber)?: EMPTY_TEXT,
                cvv = backStackEntry.arguments?.getString(NavigationRoutes.InfoCreditCard.argCardNumber)?: EMPTY_TEXT,
            )
            InfoCardScreen(creditCard = creditCard)
        }
        composable(Screen.Transactions.route) {
            Text("Transaction")
        }
    }
}