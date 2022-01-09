package dev.zenqrt.game.api

import dev.zenqrt.game.api.phase.WaitingTestPhase
import net.minestom.server.coordinate.Pos
import kotlin.random.Random

class TestGame : Game<TestGamePlayer>(Random.nextInt(100), TestGamePlayerHandler()) {
    override val startingPhase = WaitingTestPhase(this)
    val spawnPosition = Pos(10.0, 42.0, 10.0)
}