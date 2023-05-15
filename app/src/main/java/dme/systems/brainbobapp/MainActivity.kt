package dme.systems.brainbobapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dme.systems.brainbobapp.ui.theme.BrainBobAppTheme
import dme.systems.brainbobapp.ui.theme.Seal
import kotlin.text.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    val genres = listOf("Brainstorm", "Books", "Video", "Others")
    val recommendations: List<RecommendedItem> = listOf(
        RecommendedItem(
            "Chatting",
            1,
            Icons.Default.Menu,
            true,
            Color(0xFFEE776B)
        ),RecommendedItem(
            "Listen",
            2,
            Icons.Default.KeyboardArrowDown,
            false,
            Color(0xFF5446B6)
        ),RecommendedItem(
            "Speak",
            3,
            Icons.Default.Close,
            false,
            Color(0xFFe8915c)
        ),RecommendedItem(
            "Read",
            8,
            Icons.Default.Search,
            false
        ),
    )

    var isSelectedIndex by remember {
        mutableStateOf(0)
    }

    BrainBobAppTheme {
        Scaffold { it ->
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(it)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    fontSize = 40.sp,
                    modifier = Modifier.padding(bottom = 25.dp),
                    text = buildAnnotatedString {
                        append("Choose What \n")
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        ) {
                            append("to learn today?")
                        }
                    }
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(bottom = 25.dp)
                ) {
                    items(genres.size) {
                        Genre(
                            genres[it],
                            isSelectedIndex == it,
                            it,
                        ) {selectedIndex ->
                            isSelectedIndex =  selectedIndex
                        }
                    }
                }

                Box {
                    Box(
                        modifier = Modifier
                            .padding(20.dp)
                            .clip(RoundedCornerShape(10))
                            .background(colorResource(id = R.color.main))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(vertical = 30.dp)
                                    .padding(start = 20.dp)
                            ) {
                                Text(
                                    text = "Vocabulary",
                                    color = Color.White,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(
                                    modifier = Modifier.size(10.dp)
                                )

                                Text(
                                    text = "Listen to 20 new words",
                                    color = Color.White,
                                    fontSize = 20.sp
                                )

                                Spacer(modifier = Modifier.size(40.dp))

                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .size(height = 60.dp, width = 120.dp)
                                        .clip(RoundedCornerShape(20)),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.White
                                    )
                                ) {
                                    Text(
                                        text = "Start",
                                        color = Color.Black,
                                        fontSize = 18.sp
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(CircleShape)
                                            .background(Color.Black),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.PlayArrow,
                                            contentDescription = "Start",
                                            modifier = Modifier.padding(5.dp),
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.size(100.dp))
                        }

                    }
                    Image(painter = painterResource(
                        id = R.drawable.brainy
                    ),
                        contentDescription = "avatar image",
                        modifier = Modifier
                            .align(alignment = Alignment.TopEnd)
                            .size(250.dp)
                            .offset(x = 70.dp, y = -(40).dp)
                    )
                }

                Text(
                    text = "Recommended",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )

                LazyColumn(
                    modifier = Modifier.padding(end = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(recommendations.size) {
                        Recommended(item = recommendations[it])
                    }
                }
            }
        }
    }
}

@Composable
fun Genre(
    text: String,
    isSelected: Boolean = false,
    index: Int,
    onClickListener: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable {
                onClickListener(index)
            }
            .clip(RoundedCornerShape(30))
            .background(if (isSelected) Color.Black else Seal),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = text,
            color = if(isSelected) Color.White else Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Recommended(item: RecommendedItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(40))
            .background(Seal)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp)
                ) {
            Box(modifier = Modifier
                .width(55.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(30))
                .background(item.color),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(10.dp),
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Column {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text =
                    if (item.listenMinutes > 1)
                        "${item.listenMinutes} minutes"
                    else
                        "${item.listenMinutes} minute",
                    style = MaterialTheme.typography.body1
                )
            }
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = if (item.bookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "",
                    tint = if (item.bookmarked) Color.Black else Color.Gray
                )
            }
        }
    }
}