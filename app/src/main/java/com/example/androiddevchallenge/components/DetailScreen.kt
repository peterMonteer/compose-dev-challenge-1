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
package com.example.androiddevchallenge.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.data.DogsDatabase
import com.example.androiddevchallenge.data.model.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun DetailScreen(navController: NavController, id: Int) {
    Scaffold(
        topBar = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .size(30.dp, 30.dp)
                    .clickable {
                        navController.navigateUp()
                    },
                tint = Color.Black
            )
        },
        content = {
            DogDetail(DogsDatabase.dogList[id])
        }
    )
}

@Composable
fun DogDetail(dog: Dog) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(220.dp, 220.dp)
                        .clip(CircleShape)
                        .border(5.dp, Color.White, CircleShape),
                    painter = painterResource(id = dog.image),
                    alignment = Alignment.CenterStart,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                DogTag(text = dog.name, title = "Name")
            }
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DogTag(text = dog.location, title = "Location")
                DogTag(text = dog.gender, title = "Gender")
                DogTag(text = dog.age.toString(), title = "Age")
            }
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth()
            )
            Card(
                shape = RoundedCornerShape(6.dp),
                backgroundColor = Color.White,
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight()
                    .padding(10.dp),
                elevation = 6.dp

            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(0.dp, 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "About me",
                        color = Color.Black,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .padding(8.dp, 8.dp)
                            .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = dog.about,
                        color = Color.Black,
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
            }
        }
    }
}

@Composable
fun DogTag(text: String, title: String) {
    Card(
        shape = RoundedCornerShape(6.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .width(100.dp)
            .height(80.dp),
        elevation = 6.dp

    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = title,
                color = Color.Black,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                modifier = Modifier
                    .padding(8.dp, 0.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = text,
                color = Color.Black,
                style = MaterialTheme.typography.subtitle1,
            )
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    MyTheme {
        DogDetail(dog = DogsDatabase.dogList[0])
    }
}
