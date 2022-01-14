package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import io.kotest.core.spec.style.ShouldSpec
import net.minestom.server.MinecraftServer

class GameCountdownPhaseTests : ShouldSpec({
    beforeSpec { MinecraftServer.init() }

    val game = ChristmasGame(1)
    val phase = GameCountdownPhase(game, game.gameOptions, ChristmasTextFormatter())

    context("GameCountdownPhase") {
    }

})