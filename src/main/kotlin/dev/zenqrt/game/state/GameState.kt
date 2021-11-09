package dev.zenqrt.game.state

internal interface GameState {
    fun tick()
    fun shouldEnd(): Boolean
}