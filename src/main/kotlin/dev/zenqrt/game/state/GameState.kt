package dev.zenqrt.game.state

import dev.zenqrt.game.timer.Endable

interface GameState : Endable {
    fun tick()
}