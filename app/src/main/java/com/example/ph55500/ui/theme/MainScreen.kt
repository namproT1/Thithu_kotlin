package com.example.ph55500.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ph55500.model.Cat
import com.example.ph55500.viewmodel.CatViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import kotlin.OptIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: CatViewModel = viewModel()) {
    val cats by viewModel.cats.collectAsState()
    val selectedCat by viewModel.selectedCat.collectAsState()

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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(cats) { cat ->
                    CatItem(cat = cat, onClick = { viewModel.selectCat(cat) })
                }
            }

            selectedCat?.let { cat ->
                CatDetailDialog(cat = cat, onDismiss = { viewModel.dismissDialog() })
            }
        }
    }
}

@Composable
fun CatItem(cat: Cat, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = cat.image,
                contentDescription = cat.name,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = cat.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun CatDetailDialog(cat: Cat, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = cat.name) },
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
            TextButton(onClick = onDismiss) {
                Text("Đóng")
            }
        }
    )
}
