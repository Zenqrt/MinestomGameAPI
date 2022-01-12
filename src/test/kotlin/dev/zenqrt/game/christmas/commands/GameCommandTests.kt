package dev.zenqrt.game.christmas.commands

import dev.zenqrt.game.api.player.AccessibleFakePlayer
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.registry.GameRegistryService
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.maps.shouldContainKey
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import net.minestom.server.MinecraftServer
import net.minestom.server.command.builder.CommandResult
import world.cepi.kstom.Manager
import java.util.*

class GameCommandTests : ShouldSpec({

    beforeSpec {
        MinecraftServer.init()
        GameCommand.register()
    }

    context("Command") {
        should("create new game") {
            val command = Manager.command.executeServerCommand("game create")
            val id = command.commandData?.get<Int>("id")
            val game = command.commandData?.get<ChristmasGame>("game")

            GameRegistryService.find(id!!) shouldBe game
        }
    }

    context("Player command") {
        val player = AccessibleFakePlayer(UUID.randomUUID(), "test_player")
        val game = ChristmasGame.create()

        GameRegistryService.register(game.id, game)

        should("insert player to game") {
            Manager.command.execute(player, "game join ${game.id}")
            game.gamePlayers shouldContainKey player
        }

        should("force start game") {
            Manager.command.execute(player, "game start ${game.id}")
            game.startingPhase.active shouldBe true
        }
    }

})