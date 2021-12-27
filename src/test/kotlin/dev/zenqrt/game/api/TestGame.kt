package dev.zenqrt.game.api

import dev.zenqrt.game.api.phase.WaitingTestPhase
import net.minestom.server.coordinate.Pos

class TestGame : Game<TestGamePlayer>(TestGamePlayerHandler()) {
    override val startingPhase = WaitingTestPhase(this)
    val spawnPosition = Pos(10.0, 42.0, 10.0)
}