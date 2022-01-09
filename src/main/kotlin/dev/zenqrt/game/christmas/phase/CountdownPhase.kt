package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.GameOptions
import dev.zenqrt.game.christmas.timer.Countdown
import dev.zenqrt.game.christmas.timer.CountdownRunnable
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import net.minestom.server.sound.SoundEvent
import java.time.Duration

class CountdownPhase(override val eventNode: EventNode<Event>, private val game: ChristmasGame, private val gameOptions: GameOptions, private val textFormatter: ChristmasTextFormatter) : GamePhase("countdown") {
    override val nextPhase = { if(game.gamePlayers.size < gameOptions.minPlayers) WaitingPhase(game, gameOptions, textFormatter) else GameCountdownPhase(game, gameOptions, textFormatter) }
    private lateinit var countdownTask: CountdownRunnable

    override fun start() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostJoinEvent::class.java)
            .filter(GameFilter(game))) { it.game.gamePlayers.size < gameOptions.minPlayers }
        startCountdown()
    }

    private fun startCountdown() {
        val tickSound = Sound.sound(SoundEvent.BLOCK_NOTE_BLOCK_HAT, Sound.Source.NEUTRAL, 10F, 1F)
        countdownTask = Countdown.create(
            gameOptions.countdownTime,
            Duration.ofSeconds(1),
            afterIncrementAction = {
                if(it % 10 == 0 || it <= 5) {
                    game.sendActionBar(MiniMessage.get().parse(textFormatter.formatMessage("The game will start in ${textFormatter.formatNumber(it)} seconds!")))
                    game.playSound(tickSound, Sound.Emitter.self())
                }
            },
            endingAction = { switchNextPhase() }
        )
    }

    override fun end() {
        stopCountdown()
        switchAllNextPhaseEventNodes()
    }

    private fun stopCountdown() {
        countdownTask.cancel()
    }
}