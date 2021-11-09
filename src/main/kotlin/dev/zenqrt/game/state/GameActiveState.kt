package dev.zenqrt.game.state

import dev.zenqrt.game.phase.GameActivePhase

internal class GameActiveState(private val phase: GameActivePhase) : GameState {

    override fun tick() {
        if(phase.shouldEnd())
        phase.tick()
    }

    override fun shouldEnd(): Boolean = phase.shouldEnd()
}