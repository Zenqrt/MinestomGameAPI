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
        MinestomServer.registerWorlds()
    }

    context("CountdownPhase") {
        val game = ChristmasGame(1)
        val phase = createPhase(game)
        phase.startPhase()

        context("BossBar") {
            should("show player boss bar") {
                val fakePlayer = AccessibleFakePlayer(UUID.randomUUID(), "test_player")
                val gamePlayer = game.createPlayer(fakePlayer)
                game.insertPlayer(gamePlayer, fakePlayer, game)

            }
        }

        should("switch phases when min players are no longer reached") {
            val fakePlayer = AccessibleFakePlayer(UUID.randomUUID(), "test_player")
            val gamePlayer = game.createPlayer(fakePlayer)
            game.insertPlayer(gamePlayer, fakePlayer, game)
            game.removePlayer(gamePlayer, fakePlayer, game)

            phase.active shouldBe false
        }
    }

})

private fun createPhase(game: ChristmasGame): CountdownPhase = CountdownPhase(game.startingPhase.eventNode, game, game.gameOptions, ChristmasTextFormatter(), game.startingPhase)