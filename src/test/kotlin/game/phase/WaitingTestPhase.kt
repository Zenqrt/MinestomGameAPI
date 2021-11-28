package game.phase

import dev.zenqrt.game.event.GamePlayerJoinEvent
import dev.zenqrt.game.event.GamePlayerLeaveEvent
import dev.zenqrt.game.phase.GamePhase
import game.TestGame
import net.minestom.server.event.EventListener

class WaitingTestPhase(private val game: TestGame) : GamePhase("waiting") {
    override val nextPhase = { TestPhase(game) }

    override fun start() {
        eventNode.addListener(listenPhaseChangeCondition<GamePlayerJoinEvent> { it.game.players.size >= 4 }
            .filter { it.game == game }
            .build())

        eventNode.addListener(EventListener.builder(GamePlayerJoinEvent::class.java)
            .filter { it.game == game }
            .handler { it.gamePlayer.player.teleport(game.spawnPosition) }
            .build())

        eventNode.addListener(EventListener.builder(GamePlayerLeaveEvent::class.java)
            .filter { it.game == game }
            .handler { it.gamePlayer.player.kick("You left the game!") }
            .build())
    }

    override fun end() {
    }
}