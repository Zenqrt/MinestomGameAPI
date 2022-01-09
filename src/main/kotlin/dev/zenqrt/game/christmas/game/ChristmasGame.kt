package dev.zenqrt.game.christmas.game

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.AbstractGamePlayerHandler
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.phase.WaitingPhase
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.Instance

class ChristmasGame(val instance: Instance) : Game<ChristmasGamePlayer>(ChristmasGamePlayerHandler()) {
    override val startingPhase = WaitingPhase(this, GameOptions(4, 8, 30, 300), ChristmasTextFormatter())
    val spawnPos = Pos(-22.5, 65.0, 2.5)
}