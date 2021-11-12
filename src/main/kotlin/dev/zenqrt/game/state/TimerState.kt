package dev.zenqrt.game.state

import dev.zenqrt.game.phase.TimerPhase

internal open class TimerState(private val phase: TimerPhase) : GameState {
    private var timeLeft = phase.time

    override fun tick() {
        phase.tick(timeLeft)
        timeLeft--
    }

    override fun shouldEnd(): Boolean = timeLeft <= 0

}