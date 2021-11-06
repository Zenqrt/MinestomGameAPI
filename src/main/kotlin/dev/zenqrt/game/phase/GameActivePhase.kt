package dev.zenqrt.game.phase

interface GameActivePhase : GamePhase {
    fun tick()
    fun shouldEnd(): Boolean
}