package game.phase

import dev.zenqrt.game.Game
import dev.zenqrt.game.phase.GamePhase
import net.minestom.server.entity.GameMode
import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.event.player.PlayerDisconnectEvent

// TODO: Check if player is in game
class TestPhase(private val game: Game) : GamePhase("test-phase") {
    override val nextPhase: () -> GamePhase? = { null }

    override fun start() {
        eventNode.addListener(listenPhaseChangeCondition<PlayerDisconnectEvent> { true }
            .filter(GameOnlyPredicate(game))
            .build())

        eventNode.addListener(EventListener.builder(PlayerBlockBreakEvent::class.java)
            .filter(GameOnlyPredicate(game))
            .handler { it.isCancelled = true }
            .build())
    }

    override fun end() {
        game.players.forEach { it.player.gameMode = GameMode.SPECTATOR }
    }
}