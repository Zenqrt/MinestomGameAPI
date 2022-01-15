package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.TestUtils
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.phase.trait.WorkstationPhaseTrait
import io.kotest.core.spec.style.ShouldSpec

class GameActivePhaseTests : ShouldSpec({

    context("GameActivePhase") {
        val game = ChristmasGame(1)
        val phase = GameActivePhase(game, game.gameOptions, ChristmasTextFormatter())

        context("WorkstationPhaseTrait") {
            val trait = WorkstationPhaseTrait(phase.eventNode, game)
            val (fakePlayer, gamePlayer) = TestUtils.createChristmasGamePlayer(game, "test_player")
            game.insertPlayer(gamePlayer, fakePlayer, game)

            should("use workstation") {

            }
        }

    }

})