package dev.zenqrt.game.api.registry

open class MappedRegistryService<K,V> : RegistryService<K,V> {
    protected val map = mutableMapOf<K,V>()

    override fun register(key: K, obj: V) {
        map[key] = obj
    }

    override fun unregister(key: K, obj: V) {
        map.remove(key)
    }

    override fun find(key: K): V? = map[key]

}