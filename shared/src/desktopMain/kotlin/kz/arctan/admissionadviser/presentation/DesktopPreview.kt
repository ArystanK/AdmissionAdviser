package kz.arctan.admissionadviser.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import kz.arctan.admissionadviser.presentation.localization.Strings


@Preview
@Composable
fun MessagePreview() {
    MessageComposable(Strings[Strings.TYPE_YOUR_MESSAGE_HERE_KEY, Strings.Language.ENGLISH] ?: "")
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar("", {}, {}, Strings.Language.ENGLISH)
}