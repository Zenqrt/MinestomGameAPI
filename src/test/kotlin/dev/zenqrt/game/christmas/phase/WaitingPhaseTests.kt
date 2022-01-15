package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.TestUtils
import dev.zenqrt.game.api.player.AccessibleFakePlayer
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.phase.trait.PlayerActivityBossBarPhaseTrait
import dev.zenqrt.game.christmas.utils.viewers
import dev.zenqrt.game.server.MinestomServer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.floats.shouldBeExactly
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer

class WaitingPhaseTests : ShouldSpec({
    beforeSpec {
        MinecraftServer.init()
        MinestomServer.registerWorlds()
    }

    context("WaitingPhase") {
        val game = ChristmasGame(1)
        val phase = game.startingPhase

        context("PlayerActivityBossBarPhaseTrait") {
            val trait = PlayerActivityBossBarPhaseTrait(game, phase.eventNode, ChristmasTextFormatter(), game.gameOptions.maxPlayers)
            val bossBar = trait.playerCountBossBar

            should("contain current players in BossBar viewers when handling trait") {
                val (fakePlayer, gamePlayer) = TestUtils.createChristmasGamePlayer(game, "test_player")
                game.insertPlayer(gamePlayer, fakePlayer, game)

                trait.handleTrait()
                bossBar.viewers shouldContain fakePlayer

                game.removePlayer(gamePlayer, fakePlayer, game)
            }

            context("Player handling") {
                val (fakePlayer, gamePlayer) = TestUtils.createChristmasGamePlayer(game, "test_player")

                context("Insertion") {
                    game.insertPlayer(gamePlayer, fakePlayer, game)

                    should("contain inserted player in BossBar viewers upon insertion") { bossBar.viewers shouldContain fakePlayer }
                    should("set progress corresponding to the amount of players") { bossBar.progress() shouldBeExactly  1F / game.gameOptions.maxPlayers }
                    should("set display name corresponding to the amount of players") { bossBar.name() shouldBe trait.playerCountText }
                }

                context("Removal") {
                    game.removePlayer(gamePlayer, fakePlayer, game)

                    should("not contain removed player in BossBar viewers upon removal") { bossBar.viewers shouldNotContain fakePlayer }
                    should("set progress corresponding to the amount of players") { bossBar.progress() shouldBeExactly 0F }
                    should("set display name corresponding to the amount of players") { bossBar.name() shouldBe trait.playerCountText }
                }
            }

        }

        should("change phase when 4 players join") {

        }
    }

})