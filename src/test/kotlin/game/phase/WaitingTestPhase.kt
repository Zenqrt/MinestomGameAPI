package game.phase

import dev.zenqrt.game.event.GamePlayerJoinEvent
import dev.zenqrt.game.event.GamePlayerLeaveEvent
import dev.zenqrt.game.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.phase.GamePhase
import game.TestGame
import net.minestom.server.event.EventListener

class WaitingTestPhase(private val game: TestGame) : GamePhase("waiting") {
    override val nextPhase = { EndingTestPhase(game) }

    override fun start() {
        eventNode.addListener(listenPhaseChangeCondition<GamePlayerPostJoinEvent> { it.game.gamePlayers.size >= 4 }
            .filter { it.game == game }
            .build())

        eventNode.addListener(EventListener.builder(GamePlayerJoinEvent::class.java)
            .filter { it.game == game }
            .handler { it.player.health = 5f }
            .build())

        eventNode.addListener(EventListener.builder(GamePlayerLeaveEvent::class.java)
            .filter { it.game == game }
            .handler { it.player.heal() }
            .build())
    }

    override fun end() {
        game.gamePlayers.forEach { it.key.additionalHearts = 10F }
    }
}