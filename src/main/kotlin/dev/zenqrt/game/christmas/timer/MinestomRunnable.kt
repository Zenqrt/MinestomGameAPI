package dev.zenqrt.game.christmas.timer

import net.minestom.server.MinecraftServer
import net.minestom.server.timer.Task
import java.time.Duration

abstract class MinestomRunnable : Runnable {
    private var task: Task? = null
    private var repeatDuration: Duration = Duration.ZERO
    private var delayDuration: Duration = Duration.ZERO

    fun delay(duration: Duration) = this.also { delayDuration = duration }

    fun repeat(duration: Duration) = this.also { repeatDuration = duration }

    fun schedule(): Task {
        val t = MinecraftServer.getSchedulerManager().buildTask(this)
            .let { if (delayDuration != Duration.ZERO) it.delay(delayDuration) else it }
            .let { if (repeatDuration != Duration.ZERO) it.repeat(repeatDuration) else it }
            .schedule()
        this.task = t
        return t
    }

    fun cancel() = task?.cancel()
}