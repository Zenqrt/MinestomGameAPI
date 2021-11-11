package game.phase

import dev.zenqrt.game.phase.TimerPhase

class TestStartPhase : TimerPhase {

    override val time = 10L

    override fun tick(timeLeft: Long) {
    }
}