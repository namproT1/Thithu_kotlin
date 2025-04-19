
@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.ph55500

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ph55500.model.Cat
import com.example.ph55500.viewmodel.CatViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Lấy ViewModel
            val viewModel: CatViewModel = viewModel()
            val cats by viewModel.cats.collectAsState()
            var selectedCat by remember { mutableStateOf<Cat?>(null) }

            // Dùng Scaffold để xài TopAppBar cho đúng
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("🐱 Danh sách mèo dễ thương") }
                    )
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    // Danh sách mèo
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(cats) { cat ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedCat = cat }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = cat.image,
                                    contentDescription = cat.name,
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(text = cat.name)
                            }
                        }
                    }

                    selectedCat?.let { cat ->
                        AlertDialog(
                            onDismissRequest = { selectedCat = null },
                            title = { Text(cat.name) },
                            text = {
                                Column {
                                    AsyncImage(
                                        model = cat.image,
                                        contentDescription = cat.name,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(text = cat.description)
                                }
                            },
                            confirmButton = {
                                TextButton(onClick = { selectedCat = null }) {
                                    Text("Đóng")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
