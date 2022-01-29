package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.TestUtils
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.server.MinestomServer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer

class SantaSleighWorkstationHandlerTests : ShouldSpec({
    beforeSpec {
        MinecraftServer.init()
        MinestomServer.registerWorlds()
    }

    context("SantaSleighWorkstationHandler") {
        val game = ChristmasGame(1)
        val handler = SantaSleighWorkstationHandler(game)
        val (fakePlayer, gamePlayer) = TestUtils.createChristmasGamePlayer(game, "test_player")

        game.insertPlayer(gamePlayer, fakePlayer, game)

        should("not receive point from using station with empty hand") {
            handler.useStation(fakePlayer)
            game.gamePlayers[fakePlayer]!!.toysBuilt shouldBe 0
        }

        should("receive point from using station with present in hand") {
            fakePlayer.itemInMainHand = Items.WRAPPED_PRESENT.createItemStack()
            handler.useStation(fakePlayer)
            game.gamePlayers[fakePlayer]!!.toysBuilt shouldBe 1
        }
    }

})