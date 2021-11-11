package dev.zenqrt.game

internal sealed interface Registry<T> {
    fun register(key: String, obj: T)
    fun unregister(key: String, obj: T)
    fun find(key: String): T?
}