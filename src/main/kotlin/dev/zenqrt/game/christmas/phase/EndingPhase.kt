package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.phase.GamePhase

class EndingPhase : GamePhase("ending") {

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun end() {
        removeAllEventNodes()
        endTraits()
    }
}