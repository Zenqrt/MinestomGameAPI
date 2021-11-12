package game.phase

import dev.zenqrt.game.phase.GameActivePhase

class TestActivePhase : GameActivePhase {

    override fun tick() {
    }

    override fun shouldEnd(): Boolean = true
}