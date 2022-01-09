package dev.zenqrt.game.api.phase.trait

import dev.zenqrt.game.api.TestGame
import dev.zenqrt.game.api.TestGamePlayer
import dev.zenqrt.game.api.player.AccessibleFakePlayer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.floats.shouldBeExactly
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer
import java.util.*

class PhaseTraitTests : ShouldSpec({

    beforeSpec { MinecraftServer.init() }

    context("Game") {
        val game = TestGame()
        game.startGame()

        val uuid = UUID.randomUUID()
        val player = AccessibleFakePlayer(uuid, "test_player")
        game.insertPlayer(TestGamePlayer(uuid), player, game)

        should("set player exp to 0.5F when phase changes to EndingTestPhase") {
            game.startingPhase.switchNextPhase()

            player.exp shouldBeExactly 0.5F
        }

        should("set allow flying to true when phase ends") {
            player.kill()

            player.isAllowFlying shouldBe true
        }
    }

})