package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.player.AccessibleFakePlayer
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.server.MinestomServer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer
import java.util.*

class CountdownPhaseTests : ShouldSpec({
    beforeSpec {
        MinecraftServer.init()
        MinestomServer.registerAll()
    }

    context("CountdownPhase") {
        val game = ChristmasGame(1)
        val phase = CountdownPhase(game.startingPhase.eventNode, game, game.gameOptions, ChristmasTextFormatter())
        phase.active = true
        phase.startPhase()

        should("switch phases when min players are no longer reached") {
            val fakePlayer = AccessibleFakePlayer(UUID.randomUUID(), "test_player")
            val gamePlayer = game.createPlayer(fakePlayer)
            game.insertPlayer(gamePlayer, fakePlayer, game)
            game.removePlayer(gamePlayer, fakePlayer, game)

            phase.active shouldBe false
        }
    }

})