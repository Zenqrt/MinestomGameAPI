package dev.zenqrt.game.api

import dev.zenqrt.game.api.player.AccessibleFakePlayer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.maps.shouldNotContain
import net.minestom.server.MinecraftServer
import java.util.*

class GamePlayerTests : ShouldSpec({

    beforeSpec { MinecraftServer.init() }

    context("GamePlayer") {
        val game = TestGame()
        game.startGame()

        val player = AccessibleFakePlayer(UUID.randomUUID(), "TestPlayer")
        val gamePlayer = game.createPlayer(player)

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