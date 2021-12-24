package game.phase

import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import dev.zenqrt.game.api.event.GamePlayerLeaveEvent
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.api.phase.phase.MaxPlayersEventTrait
import game.TestGame
import net.minestom.server.event.EventListener

class WaitingTestPhase(private val game: TestGame) : GamePhase("waiting") {
    override val nextPhase = { EndingTestPhase(game) }
    private val maxPlayers = 4

    override fun start() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostJoinEvent::class.java)
            .filter(GameFilter(game))) { it.game.gamePlayers.size >= maxPlayers }

        eventNode.addListener(EventListener.builder(GamePlayerJoinEvent::class.java)
            .filter(GameFilter(game))
            .handler { it.player.health = 5f }
            .build())

        eventNode.addListener(EventListener.builder(GamePlayerLeaveEvent::class.java)
            .filter(GameFilter(game))
            .handler { it.player.heal() }
            .build())

        addTrait(MaxPlayersEventTrait(eventNode, EventListener.builder(GamePlayerJoinEvent::class.java), game, maxPlayers))
    }

    override fun end() {
        game.gamePlayers.forEach { it.key.additionalHearts = 10F }
    }
}