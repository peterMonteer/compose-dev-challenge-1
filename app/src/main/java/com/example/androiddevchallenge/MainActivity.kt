/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.components.ItemDogCard
import com.example.androiddevchallenge.components.TopBar
import com.example.androiddevchallenge.data.DogsDatabase
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MainView()
            }
        }
    }
}

@Composable
fun MyApp(navController: NavController) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            TopBar()
            LazyColumn {
                items(DogsDatabase.dogList) {
                    DogsDatabase.dogList.forEach {
                        ItemDogCard(
                            it,
                            onItemClicked = { dog ->
                                navController.navigate("detail/${dog.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}
