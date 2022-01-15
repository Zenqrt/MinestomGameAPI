package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.player.AccessibleFakePlayer
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.ChristmasGamePlayer
import dev.zenqrt.game.christmas.leaderboard.ChristmasLeaderboardCalculator
import dev.zenqrt.game.christmas.phase.trait.DisplayLeaderboardPhaseTrait
import dev.zenqrt.game.server.MinestomServer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer
import java.util.*

class EndingPhaseTests : ShouldSpec({
    beforeSpec {
        MinecraftServer.init()
        MinestomServer.registerWorlds()
    }

    context("EndingPhase") {
        val game = ChristmasGame(1)
        val phase = EndingPhase(game, ChristmasTextFormatter())

        context("DisplayLeaderboardPhaseTrait") {
            val trait = DisplayLeaderboardPhaseTrait(game, ChristmasTextFormatter(), ChristmasLeaderboardCalculator)

            should("calculate leaderboard") {
                val firstPlace = insertGamePlayer(game, "first_place", 5)
                val secondPlace = insertGamePlayer(game, "second_place", 3)
                val thirdPlace = insertGamePlayer(game, "third_place", 1)

                trait.calculateLeaderboard()

                trait.leaderboard[0].second shouldBe firstPlace
                trait.leaderboard[1].second shouldBe secondPlace
                trait.leaderboard[2].second shouldBe thirdPlace
            }
        }
    }

})

private fun insertGamePlayer(game: ChristmasGame, name: String, toysBuilt: Int): ChristmasGamePlayer = AccessibleFakePlayer(UUID.randomUUID(), name).let {
    ChristmasGamePlayer(it.uuid, toysBuilt).also { gamePlayer -> game.insertPlayer(gamePlayer, it, game) }
}