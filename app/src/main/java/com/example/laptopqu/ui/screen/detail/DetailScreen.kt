package com.example.laptopqu.ui.screen.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.laptopqu.R
import com.example.laptopqu.data.LaptopRepository
import com.example.laptopqu.ui.viewmodel.LaptopDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    laptopId: Int,
    navController: NavController,
    viewModel: LaptopDetailViewModel = viewModel(
        factory = LaptopDetailViewModel.Factory(
            LaptopRepository()
        )
    )
) {
    val context = LocalContext.current
    val laptop = viewModel.getLaptopById(laptopId)

    if (laptop != null) {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("Detail Laptop") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_back),
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            },
            content = { paddingValues: PaddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = laptop.photoUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .size(220.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = laptop.name,
                                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = laptop.price,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                                color = Color.DarkGray,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            Text(
                                text = "Specifications:",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = laptop.specification,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Berminat? Hubungi sekarang",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color.DarkGray,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                IconButton(
                                    onClick = { sendEmail(context) },
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_email),
                                        contentDescription = "Email",
                                        modifier = Modifier.size(40.dp)
                                    )
                                }

                                IconButton(
                                    onClick = { sendWhatsApp(context) },
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_whatsapp),
                                        contentDescription = "WhatsApp",
                                        modifier = Modifier.size(40.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    } else {
        Text("Laptop not found.", style = MaterialTheme.typography.bodyLarge, color = Color.Red)
    }
}

private fun sendEmail(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:harmadedani@gmail.com")
        putExtra(Intent.EXTRA_SUBJECT, "Inquiry about Laptop")
    }
    context.startActivity(intent)
}

private fun sendWhatsApp(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/6287796622516")).apply {
        putExtra(Intent.EXTRA_TEXT, "Hello, I'm interested in the laptop.")
    }
    context.startActivity(intent)
}
