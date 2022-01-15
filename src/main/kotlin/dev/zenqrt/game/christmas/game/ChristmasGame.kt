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

class ChristmasGame(id: Int, val gameOptions: GameOptions = GameOptions(1, 8, 30, 300)) : Game<ChristmasGamePlayer>(id, ChristmasGamePlayerHandler()) {
    override val startingPhase = WaitingPhase(this, gameOptions, ChristmasTextFormatter())
    val christmasMapWorld = ChristmasMapWorld()
    val instance: Instance

    init {
        instance = christmasMapWorld.createInstanceContainer()
        instance.timeRate = 0
    }

    override fun forceStartGame(): Boolean {
        return if(startingPhase.active) {
            startingPhase.switchNextPhase()
            true
        } else false
    }

    companion object {
        fun create(): ChristmasGame = ChristmasGame(GameRegistryService.findAvailableId())
    }
}