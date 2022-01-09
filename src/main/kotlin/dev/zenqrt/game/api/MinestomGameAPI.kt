package dev.zenqrt.game.api

import dev.zenqrt.game.api.registry.GameRegistryService

object MinestomGameAPI {
    val gameRegistry = GameRegistryService()
    fun init() {
    }

}