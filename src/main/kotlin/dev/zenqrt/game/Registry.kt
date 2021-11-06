package dev.zenqrt.game

sealed interface Registry<T> {
    fun register(obj: T)
    fun unregister(obj: T)
}