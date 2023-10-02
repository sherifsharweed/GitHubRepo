package com.example.githubrepo.ui.screens.main.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubrepo.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseSearch: () -> Unit,
    modifier: Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        TextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onTextChange,
            textStyle = MaterialTheme.typography.displayLarge,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_hint),
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.alpha(0.5f)
                )
            },
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = {
                    onSearchClicked(text)
                    keyboardController?.hide()
                }, modifier = Modifier.alpha(0.5f)) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_icon),
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    onCloseSearch()
                    keyboardController?.hide()
                }, modifier = Modifier.alpha(0.5f)) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "CLOSE_ICON",
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchClicked(text)
                keyboardController?.hide()
            }), colors =
            TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.background,
                textColor = MaterialTheme.colorScheme.background,
                containerColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )
    }
}

@Preview
@Composable
fun PreviewSearchApp() {
    SearchAppBar(
        text = "",
        onTextChange = {},
        onSearchClicked = {},
        onCloseSearch = {},
        modifier = Modifier
            .height(60.dp)
            .background(MaterialTheme.colorScheme.secondary, shape = CircleShape)
            .padding(horizontal = 10.dp)
    )
}