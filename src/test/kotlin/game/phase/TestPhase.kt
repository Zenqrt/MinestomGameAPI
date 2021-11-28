package game.phase

import dev.zenqrt.game.Game
import dev.zenqrt.game.phase.GamePhase
import dev.zenqrt.game.predicate.PlayerGameOnlyPredicate
import net.minestom.server.entity.GameMode
import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.event.player.PlayerStartSneakingEvent

class TestPhase(private val game: Game) : GamePhase("test-phase") {
    override val nextPhase: () -> GamePhase? = { EndingTestPhase(game) }

    override fun start() {
        eventNode.addListener(listenPhaseChangeCondition<PlayerStartSneakingEvent> { true }
            .filter(PlayerGameOnlyPredicate(game))
            .build())

        eventNode.addListener(EventListener.builder(PlayerBlockBreakEvent::class.java)
            .filter(PlayerGameOnlyPredicate(game))
            .handler { it.isCancelled = true }
            .build())
    }

    override fun end() {
        game.players.forEach { it.value.player.gameMode = GameMode.SPECTATOR }
    }
}