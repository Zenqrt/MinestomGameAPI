package dev.zenqrt.game.registry

internal interface Registry<K,V> {
    fun register(key: K, obj: V)
    fun unregister(key: K, obj: V)
    fun find(key: K): V?
}