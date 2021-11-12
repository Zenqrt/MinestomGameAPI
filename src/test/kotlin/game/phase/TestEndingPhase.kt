package game.phase

import dev.zenqrt.game.phase.TimerPhase

class TestEndingPhase : TimerPhase {

    override val time = 5L
    override fun tick(timeLeft: Long) {
    }
}