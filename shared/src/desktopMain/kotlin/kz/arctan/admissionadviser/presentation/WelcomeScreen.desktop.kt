package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.arctan.admissionadviser.presentation.localization.Strings
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun WelcomeScreen(state: MainState, flatMap: (MainIntent) -> Unit) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 54.dp).padding(top = 20.dp),
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
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 54.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.4F)
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Box {
                        Text(
                            text = "AITU",
                            style = TextStyle(
                                fontSize = 96.sp,
                                fontWeight = FontWeight(800),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Start
                            )
                        )
                    }
                    Box(modifier = Modifier.padding(bottom = 20.dp)) {
                        Text(
                            text = "Admissions",
                            style = TextStyle(
                                fontSize = 36.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Start
                            )
                        )
                    }
                }
                Spacer(
                    Modifier
                        .width(65.dp)
                        .height(4.dp)
                        .background(color = Color(0xFF000000))
                )
                Text(
                    text = Strings[Strings.WELCOME_TEXT, state.language],
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier.padding(top = 28.dp)
                )
                Text(
                    text = Strings[Strings.WELCOME_TEXT_DESKTOP, state.language],
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier.padding(vertical = 50.dp)
                )
                Button(
                    onClick = { flatMap(MainIntent.OpenPageIdChangeMainIntent(1)) },
                    modifier = Modifier
                        .width(130.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF21CCF2))
                ) {
                    Text(
                        text = Strings[Strings.START, state.language],
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFEBEBEB),
                        )
                    )
                }
            }
            Column(modifier = Modifier.weight(0.4F)) {
                Image(
                    painter = painterResource("drawable/laptop.png"),
                    contentDescription = "Laptop",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(700.dp)
                        .height(700.dp)
                )
            }
        }
    }
}