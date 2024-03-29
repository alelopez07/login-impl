package dev.jorgealejandro.sdet.code_challenge.ui.base

import androidx.lifecycle.ViewModel
import dev.jorgealejandro.sdet.code_challenge.utils.launch
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel : ViewModel() {
    abstract fun onUIEvent(event: UIEvent)
    protected fun exec(action: suspend () -> Unit) {
        launch(Dispatchers.IO) { action() }
    }
}