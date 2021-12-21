package game

import dev.zenqrt.game.GamePlayer
import game.player.AccessibleFakePlayer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.floats.shouldBeExactly
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.damage.DamageType
import java.util.*

class GameEventTests : ShouldSpec({

    beforeSpec { MinecraftServer.init() }

    context("Game") {
        val game = TestGame()
        game.startingPhase.start()

        context("GamePlayer") {
            val gamePlayer = GamePlayer()
            val player = AccessibleFakePlayer(UUID.randomUUID(), "test_player")

            should("set player health to 5 HP upon insertion") {
                game.insertPlayer(gamePlayer, player, game)
                player.health shouldBeExactly 5F
            }

            should("reset player health to max HP upon removal") {
                game.removePlayer(gamePlayer, player, game)
                player.health shouldBeExactly player.maxHealth
            }

            should("remove event node from previous GamePhase") {
                val eventNode = game.startingPhase.eventNode

                for(i in 1..4) {
                    game.insertPlayer(GamePlayer(), AccessibleFakePlayer(UUID.randomUUID(), "test_player$i"), game)
                }

                MinecraftServer.getGlobalEventHandler().children shouldNotContain eventNode
            }

            should("set all player additional hearts to 10F when the phase ends") {
                game.gamePlayers.forEach {
                    it.key.additionalHearts shouldBeExactly 10F }
            }

            should("set player glowing to true upon damage") {
                val firstPlayer = game.gamePlayers.keys.first()
                firstPlayer.damage(DamageType.VOID, 1F)

                firstPlayer.isGlowing shouldBe true
            }

            should("clear player list upon death") {
                val firstPlayer = game.gamePlayers.keys.first()
                firstPlayer.kill()

                game.gamePlayers.size shouldBeExactly 0
            }
        }
    }

})