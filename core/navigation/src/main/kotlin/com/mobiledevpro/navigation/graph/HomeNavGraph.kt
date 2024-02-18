/*
 * Copyright 2022 | Dmitri Chernysh | https://mobile-dev.pro
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.mobiledevpro.navigation.graph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mobiledevpro.home.view.HomeScreen
import com.mobiledevpro.home.view.HomeViewModel
import com.mobiledevpro.navigation.Screen
import com.mobiledevpro.navigation.host.HomeNavHost


fun NavGraphBuilder.homeNavGraph(onNavigateToRoot: (Screen) -> Unit) {
    composable(
        route = Screen.Home.route
    ) {

        //NavController for nested graph
        //It will not work for root graph
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val nestedNavGraph: @Composable () -> Unit = {
            Log.d("navigation", "homeNavGraph:nestedNavGraph")
            HomeNavHost(
                navController = navController,
                onNavigateToRoot = onNavigateToRoot
            )
        }

        val viewModel: HomeViewModel = viewModel()

        HomeScreen(
            nestedNavGraph = nestedNavGraph
        )

    }

}



