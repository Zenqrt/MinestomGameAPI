package dev.zenqrt.game.christmas.registry

import dev.zenqrt.game.api.registry.MappedRegistryService
import dev.zenqrt.game.christmas.game.ChristmasGame
import kotlin.random.Random

object GameRegistryService : MappedRegistryService<Int, ChristmasGame>() {
    fun findAvailableId(): Int {
        val id = Random.nextInt(10000)

        return if(find(id) != null) findAvailableId() else id
    }
}