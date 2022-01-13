package dev.zenqrt.game.api.phase.trait

import dev.zenqrt.game.TestUtils
import dev.zenqrt.game.api.TestGame
import dev.zenqrt.game.api.phase.WaitingTestPhase
import dev.zenqrt.game.api.phase.WaitingTestPhaseKeepEventNode
import dev.zenqrt.game.api.phase.WaitingTestPhaseKeepTraits
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.floats.shouldBeExactly
import io.kotest.matchers.floats.shouldNotBeExactly
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer

class PhaseTraitTests : ShouldSpec({

    beforeSpec { MinecraftServer.init() }

    context("WaitingTestPhase") {
        val game = TestGame()
        val phase = WaitingTestPhase(game)
        phase.startPhase()

        val player = TestUtils.registerFakePlayer(game)

        should("set player exp to 0.5F when phase changes to EndingTestPhase") {
            phase.switchNextPhase()

            player.exp shouldBeExactly 0.5F
        }

        should("not set player health to 5F") {
            TestUtils.registerFakePlayer(game).health shouldNotBeExactly 5F
        }

        should("set allow flying to true when phase ends") {
            player.kill()

            player.isAllowFlying shouldBe true
        }
    }

    context("WaitingTestPhaseKeepEventNode") {
        val game = TestGame()
        val phase = WaitingTestPhaseKeepEventNode(game)
        phase.startPhase()
        phase.switchNextPhase()


        should("still set player health to 5F on join") {
            val player = TestUtils.registerFakePlayer(game)
            player.health shouldBeExactly 5F
        }
    }

    context("WaitingTestPhaseKeepTraits") {
        val game = TestGame()
        val phase = WaitingTestPhaseKeepTraits(game)
        phase.startPhase()
        phase.switchNextPhase()

        should("still keep player count at 4 players when trying to add a 5th player") {
            for(i in 0 until 5) {
                TestUtils.registerFakePlayer(game)
            }

            game.gamePlayers.size shouldBe 4
        }
    }
})