package dev.zenqrt.game.api.phase

import dev.zenqrt.game.api.TestGame
import dev.zenqrt.game.api.player.AccessibleFakePlayer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.floats.shouldBeExactly
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.damage.DamageType
import net.minestom.server.event.entity.EntityDamageEvent
import net.minestom.server.event.player.PlayerDeathEvent
import world.cepi.kstom.Manager
import java.util.*

class GamePhaseTests : ShouldSpec({

    beforeSpec { MinecraftServer.init() }

    context("Game") {
        val game = TestGame()
        game.startGame()

        context("GamePlayer") {
            val player = AccessibleFakePlayer(UUID.randomUUID(), "test_player")
            val gamePlayer = game.createPlayer(player)

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
                    val fakePlayer = AccessibleFakePlayer(UUID.randomUUID(), "test_player$i")
                    game.insertPlayer(game.createPlayer(fakePlayer), fakePlayer, game)
                }

                MinecraftServer.getGlobalEventHandler().children shouldNotContain eventNode
            }

            should("set all player additional hearts to 10F when the phase ends") {
                game.gamePlayers.forEach {
                    it.key.additionalHearts shouldBeExactly 10F }
            }

            should("set player glowing to true upon damage") {
                val firstPlayer = game.gamePlayers.keys.first()
                Manager.globalEvent.call(EntityDamageEvent(firstPlayer, DamageType.VOID, 1F, null))

                firstPlayer.isGlowing shouldBe true
            }

            should("clear player list upon death") {
                val firstPlayer = game.gamePlayers.keys.first()
                Manager.globalEvent.call(PlayerDeathEvent(firstPlayer, Component.empty(), Component.empty()))

                game.gamePlayers.size shouldBeExactly 0
            }
        }
    }

})