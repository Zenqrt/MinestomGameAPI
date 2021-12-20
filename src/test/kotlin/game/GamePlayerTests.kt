package game

import dev.zenqrt.game.GamePlayer
import dev.zenqrt.game.registry.GamePlayerRegistry
import game.player.AccessibleFakePlayer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.maps.shouldNotContain
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer
import java.util.*

class GamePlayerTests : ShouldSpec({

    beforeSpec { MinecraftServer.init() }

    context("GamePlayer") {
        val game = TestGame()
        val registry = GamePlayerRegistry()
        game.startingPhase.start()

        val gamePlayer = GamePlayer()
        val player = AccessibleFakePlayer(UUID.randomUUID(), "TestPlayer")

        should("register fake player into registry") {
            registry.register(gamePlayer.uuid, gamePlayer)
            registry.find(gamePlayer.uuid) shouldBe gamePlayer
        }

        should("insert to players list") {
            game.insertPlayer(gamePlayer, player, game)
            game.gamePlayers shouldContain Pair(player, gamePlayer)
        }

        should("remove from player list") {
            game.removePlayer(gamePlayer, player, game)
            game.gamePlayers shouldNotContain Pair(player, gamePlayer)
        }

    }

})