package com.appenginex.com.common.navigation

import com.appenginex.com.common.navigation.NavigationRoutes.InfoCreditCardRoute

sealed class Screen(val route: String) {
    object Dashboard : Screen("main")
    object InfoCreditCard : Screen(InfoCreditCardRoute)
    object Transactions : Screen("transactions")
}