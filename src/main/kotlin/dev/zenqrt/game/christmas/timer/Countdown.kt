package dev.zenqrt.game.christmas.timer

import java.time.Duration

object Countdown {
    fun create(initialTime: Int,
               duration: Duration,
               beforeIncrementAction: (Int) -> Unit = {},
               afterIncrementAction: (Int) -> Unit = {},
               endingAction: () -> Unit = {},
               cancelCondition: (Int) -> Boolean = { false }): CountdownRunnable = CountdownRunnable(initialTime, beforeIncrementAction, afterIncrementAction, endingAction, cancelCondition).also { it.repeat(duration).schedule() }
}

class CountdownRunnable(initialTime: Int,
                private val beforeIncrementAction: (Int) -> Unit,
                private val afterIncrementAction: (Int) -> Unit,
                private val endingAction: () -> Unit,
                private val cancelCondition: (Int) -> Boolean) : MinestomRunnable() {
    private var time = initialTime

    override fun run() {
        if(cancelCondition(time)) {
            endCountdown()
            return
        }
        handleCountdown()
    }

    private fun handleCountdown() {
        beforeIncrementAction(time)
        time--
        afterIncrementAction(time)
    }

    private fun endCountdown() {
        cancel()
        endingAction()
    }
}