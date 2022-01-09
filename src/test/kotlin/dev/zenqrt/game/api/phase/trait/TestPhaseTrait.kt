package dev.zenqrt.game.api.phase.trait

import dev.zenqrt.game.api.TestGame

class TestPhaseTrait(private val game: TestGame) : PhaseTrait {
    override fun handleTrait() {
        game.broadcast { it.exp = 0.5F }
    }

    override fun endTrait() {
        game.broadcast { it.isAllowFlying = true }
    }
}