package game.phase

import dev.zenqrt.game.event.filter.GamePlayerFilter
import dev.zenqrt.game.phase.GamePhase
import game.TestGame
import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerChatEvent
import net.minestom.server.event.player.PlayerStartSneakingEvent
import net.minestom.server.event.player.PlayerStartSprintingEvent

class EndingTestPhase(private val game: TestGame) : GamePhase("ending") {

    override fun start() {
        eventNode.addListener(listenPhaseChangeCondition<PlayerStartSprintingEvent> { true }
            .filter(GamePlayerFilter(game))
            .build())

        eventNode.addListener(EventListener.builder(PlayerChatEvent::class.java)
            .filter(GamePlayerFilter(game))
            .handler { it.player.level = 5 }
            .build())
    }

    override fun end() {
        game.gamePlayers.clear()
    }
}