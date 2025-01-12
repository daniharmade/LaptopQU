package com.example.laptopqu.ui.screen.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
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
    ) // Pass the repository to viewModel
) {
    // Get the context from LocalContext
    val context = LocalContext.current

    // Fetch the laptop details using laptopId from the ViewModel
    val laptop = viewModel.getLaptopById(laptopId)

    if (laptop != null) {
        // Show the laptop details if available
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
                    // Display laptop photo with rounded corners
                    Image(
                        painter = painterResource(id = laptop.photoUrl), // Use the correct resource ID for the image
                        contentDescription = null,
                        modifier = Modifier
                            .size(220.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the image
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Card to display laptop details in a structured way
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            // Display laptop name with a large, bold font
                            Text(
                                text = laptop.name,
                                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            // Display laptop price with a prominent font style
                            Text(
                                text = laptop.price,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                                color = Color.DarkGray,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // Section title for Specifications
                            Text(
                                text = "Specifications:",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            // Display laptop specifications
                            Text(
                                text = laptop.specification,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Add message text for contact
                            Text(
                                text = "Berminat? Hubungi sekarang",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color.DarkGray,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            // Add buttons to contact via email or WhatsApp
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                // Email Button
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

                                // WhatsApp Button
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
        // Display a placeholder or error message if laptop is not found
        Text("Laptop not found.", style = MaterialTheme.typography.bodyLarge, color = Color.Red)
    }
}

// Inside your composable
private fun sendEmail(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:harmadedani@gmail.com") // Email address
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
