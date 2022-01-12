package dev.zenqrt.game.christmas.game

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.AbstractGamePlayerHandler
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.phase.WaitingPhase
import dev.zenqrt.game.christmas.registry.GameRegistryService
import dev.zenqrt.game.christmas.world.worlds.ChristmasMapWorld
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.Instance
import world.cepi.kstom.Manager

class ChristmasGame(id: Int) : Game<ChristmasGamePlayer>(id, ChristmasGamePlayerHandler()) {
    override val startingPhase = WaitingPhase(this, GameOptions(4, 8, 30, 300), ChristmasTextFormatter())
    val christmasMapWorld = ChristmasMapWorld()
    val instance: Instance

    init {
        instance = Manager.instance.createInstanceContainer()
        instance.timeRate = 0
    }

    companion object {
        fun create(): ChristmasGame = ChristmasGame(GameRegistryService.findAvailableId())
    }
}