package dev.zenqrt.game.phase

interface TimerPhase : GamePhase {
    val time: Long
    fun tick(timeLeft: Long)
}