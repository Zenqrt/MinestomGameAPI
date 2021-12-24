package game

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayerHandlerImpl
import game.phase.WaitingTestPhase
import net.minestom.server.coordinate.Pos

class TestGame : Game(GamePlayerHandlerImpl()) {
    override val startingPhase = WaitingTestPhase(this)
    val spawnPosition = Pos(10.0, 42.0, 10.0)
}