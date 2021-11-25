package game.phase

import dev.zenqrt.game.phase.GamePhase
import net.minestom.server.event.player.PlayerDisconnectEvent

// TODO: Check if player is in game
class TestPhase : GamePhase("test-phase") {
    override val nextPhase: () -> GamePhase? = { null }

    override fun start() {
        listenPhaseChangeCondition<PlayerDisconnectEvent> { true }
    }

    override fun end() {
        TODO("Not yet implemented")
    }
}