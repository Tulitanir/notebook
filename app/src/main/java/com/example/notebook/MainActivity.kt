package com.example.notebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notebook.pages.AddItemPage
import com.example.notebook.pages.AddTagPage
import com.example.notebook.pages.EditItemPage
import com.example.notebook.pages.EditTagPage
import com.example.notebook.pages.NotebookPage
import com.example.notebook.pages.TagPage
import com.example.notebook.ui.theme.NotebookTheme
import com.example.notebook.viewmodel.NotebookItemViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NotebookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = Routes.RECORDS, builder = {
                        composable(Routes.RECORDS) {
                            NotebookPage(innerPadding, navController)
                        }
                        composable(Routes.TAGS) {
                            TagPage(innerPadding, navController)
                        }
                        composable(Routes.ADD_ITEM) {
                            AddItemPage(navController)
                        }
                        composable(Routes.ADD_TAG) {
                            AddTagPage(navController)
                        }
                        composable(Routes.EDIT_ITEM + "/{id}") {
                            val res = it.arguments?.getString("id")
                            val id = res?.toInt()
                            EditItemPage(navController, id?:0)
                        }
                        composable(Routes.EDIT_TAG + "/{id}") {
                            val res = it.arguments?.getString("id")
                            val id = res?.toInt()
                            EditTagPage(navController, id?:0)
                        }
                    } )
                }
            }
        }
    }
}