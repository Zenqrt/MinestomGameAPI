package dev.zenqrt.game.state

sealed interface GameState {
    fun tick()
    fun shouldEnd(): Boolean
}