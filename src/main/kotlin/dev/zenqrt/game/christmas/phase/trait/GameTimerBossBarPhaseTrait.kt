package dev.zenqrt.game.christmas.phase.trait

import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.api.phase.trait.PhaseTrait
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.timer.Countdown
import dev.zenqrt.game.christmas.timer.CountdownRunnable
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import world.cepi.kstom.adventure.asMini
import java.time.Duration

class GameTimerBossBarPhaseTrait(private val audience: Audience, private val phase: GamePhase, private val time: Int) : PhaseTrait {
    private val bossBar = BossBar.bossBar(getGameTimerText(time), getGameTimerProgress(time), BossBar.Color.BLUE, BossBar.Overlay.PROGRESS)
    private lateinit var countdownTask: CountdownRunnable

    override fun handleTrait() {
        audience.showBossBar(bossBar)

        countdownTask = Countdown.schedule(
            initialTime = 300,
            duration = Duration.ofSeconds(1),
            beforeIncrementAction = { updateGameTimerBossBar(it) },
            endingAction = { phase.switchNextPhase() }
        )
    }

    private fun updateGameTimerBossBar(time: Int) {
        bossBar.name(getGameTimerText(time))
        bossBar.progress(getGameTimerProgress(time))
    }

    private fun getGameTimerText(time: Int): Component = "<blue>Game Time: <aqua>${time}s".asMini()
    private fun getGameTimerProgress(time: Int): Float = time.toFloat() / this.time.toFloat()

    override fun endTrait() {
        countdownTask.cancel()
    }
}