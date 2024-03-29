package dev.jorgealejandro.sdet.code_challenge.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

inline fun ViewModel.launch(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline block: suspend CoroutineScope.() -> Unit
): Job {
    val executor: CoroutineContext by lazy { dispatcher }
    return viewModelScope.launch(executor) { block() }
}
