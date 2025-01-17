package com.mikepenz.aboutlibraries.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.entity.Library

/**
 * Displays all provided libraries in a simple list.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LibrariesContainer(
    aboutLibsJson: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    showAuthor: Boolean = true,
    showVersion: Boolean = true,
    showLicenseBadges: Boolean = true
) {
    val libraries = remember { mutableStateOf<Libs?>(null) }
    LaunchedEffect(libraries) {
        libraries.value = Libs.Builder().withJson(aboutLibsJson).build()
    }

    val libs = libraries.value?.libraries
    if (libs != null) {
        Libraries(
            libraries = libs,
            modifier,
            contentPadding,
            showAuthor,
            showVersion,
            showLicenseBadges
        )
    }
}

/**
 * Displays all provided libraries in a simple list.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Libraries(
    libraries: List<Library>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    showAuthor: Boolean = true,
    showVersion: Boolean = true,
    showLicenseBadges: Boolean = true,
) {
    LazyColumn(modifier, contentPadding = contentPadding) {
        items(libraries) { library ->
            Library(library, showAuthor, showVersion, showLicenseBadges) {}
        }
    }
}