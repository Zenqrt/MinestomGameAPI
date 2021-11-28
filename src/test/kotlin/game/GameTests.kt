package game

import game.player.FakeGamePlayer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.maps.shouldContain
import net.minestom.server.MinecraftServer

class GameTests : ShouldSpec({

    beforeSpec { MinecraftServer.init() }

    context("Game") {
        val game = TestGame()
        game.startingPhase.start()

        should("insert player to players list") {
            val gamePlayer = FakeGamePlayer("test")
            game.insertPlayer(gamePlayer, game)

            game.players shouldContain Pair(gamePlayer.player.uuid, gamePlayer)
        }
    }

})