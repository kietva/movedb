package com.example.movedb.core.base

import kotlinx.coroutines.CoroutineExceptionHandler

class EmptyViewModel() : BaseViewModel() {
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("KIETVA ${exception.message}")
    }
}