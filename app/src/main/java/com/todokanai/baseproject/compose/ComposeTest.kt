package com.todokanai.baseproject.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.todokanai.baseproject.viewmodel.compose.ComposeTestViewModel

@Composable
fun ComposeTest(
    modifier: Modifier = Modifier,
    viewModel: ComposeTestViewModel = hiltViewModel()
){
    //... Compose part
}