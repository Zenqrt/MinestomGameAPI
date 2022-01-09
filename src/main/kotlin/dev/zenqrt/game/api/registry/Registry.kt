package dev.zenqrt.game.api.registry

interface Registry<K,V> {
    fun register(key: K, obj: V)
    fun unregister(key: K, obj: V)
    fun find(key: K): V?
}