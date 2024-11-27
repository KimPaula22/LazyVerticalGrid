package com.example.lazyverticalgrid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazyverticalgrid.ui.theme.LazyVerticalGridTheme


data class ImageItem(val imageResId: Int, val title: String)

val imageList = listOf(
    ImageItem(R.drawable.nutriabebe, "Nutria bebé"),
    ImageItem(R.drawable.pandabebe, "Panda bebé"),
    ImageItem(R.drawable.panterabebe, "Pantera bebé"),
    ImageItem(R.drawable.pandarojo, "Panda Rojo bebé"),
    ImageItem(R.drawable.tigreblancobebe, "Tigre Blanco bebé"),
    ImageItem(R.drawable.zorradeldesierto, "Zorro del desierto")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyVerticalGridTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GalleryScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GalleryScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(imageList.size) { index ->
            val item = imageList[index]
            ImageWithTitle(item) {
                Toast.makeText(context, "Título: ${item.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun ImageWithTitle(item: ImageItem, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.title,
            style = TextStyle(fontSize = 16.sp, color = Color.Black)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGalleryScreen() {
    LazyVerticalGridTheme {
        GalleryScreen()
    }
}