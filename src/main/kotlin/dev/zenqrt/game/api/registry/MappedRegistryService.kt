package dev.zenqrt.game.api.registry

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import kotlin.random.Random

open class MappedRegistryService<K,V> : RegistryService<K,V> {
    private val map = mutableMapOf<K,V>()

    override fun register(key: K, obj: V) {
        map[key] = obj
    }

    override fun unregister(key: K, obj: V) {
        map.remove(key)
    }

    override fun find(key: K): V? = map[key]

}