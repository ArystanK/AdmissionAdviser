package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.arctan.admissionadviser.presentation.localization.Strings
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    WelcomeScreen(MainState()) {}
}

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun WelcomeScreen(state: MainState, flatMap: (MainIntent) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painterResource("drawable/astana_it_logo.png"),
                    "Astana IT logo",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.width(114.dp).height(62.dp).padding(8.dp)
                )
                Row {
                    Row {
                        TextButton(onClick = { flatMap(MainIntent.LanguageChangeMainIntent(Strings.Language.ENGLISH)) }) {
                            Text(Strings[Strings.ENGLISH, state.language] ?: "", color = Color.Black)
                        }
//                        TextButton(onClick = { flatMap(MainIntent.LanguageChangeMainIntent(Strings.Language.KAZAKH)) }) {
//                            Text(Strings[Strings.KAZAKH, state.language] ?: "", color = Color.Black)
//                        }
                        TextButton(onClick = { flatMap(MainIntent.LanguageChangeMainIntent(Strings.Language.RUSSIAN)) }) {
                            Text(Strings[Strings.RUSSIAN, state.language] ?: "", color = Color.Black)
                        }
                    }
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it).fillMaxSize()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(146.dp)
                                .background(color = Color(0xFF21CCF2))
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Column {
                                    Text(
                                        text = "AITU",
                                        style = TextStyle(
                                            fontSize = 90.sp,
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFFFFFFFF),
                                        )
                                    )
                                    Row {
                                        Spacer(Modifier.width(200.dp))
                                        Text(
                                            text = "Admissions",
                                            style = TextStyle(
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight(700),
                                                color = Color(0xFFFFFFFF),
                                            )
                                        )
                                    }
                                }
                            }
                        }
                        Box(Modifier.fillMaxWidth().height(170.dp))
                        Box(Modifier.fillMaxWidth().height(120.dp).background(color = Color(0xFF21CCF2)))
                    }
                    Image(
                        painter = painterResource("drawable/laptop.png"),
                        contentDescription = "Laptop",
                        modifier = Modifier
                            .width(380.dp)
                            .height(380.dp)
                            .padding(top = 90.dp)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().padding(20.dp)
                ) {
                    Text(
                        text = Strings[Strings.WELCOME_TEXT, state.language] ?: "",
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )
                    Button(
                        onClick = { flatMap(MainIntent.OpenPageIdChangeMainIntent(1)) },
                        modifier = Modifier
                            .width(314.dp)
                            .padding(top = 20.dp),
                        shape = RoundedCornerShape(size = 100.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF21CCF2))
                    ) {
                        Text(
                            text = Strings[Strings.START, state.language] ?: "",
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
            }
        }
    }
}