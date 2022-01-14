package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.GamePlayerPostLeaveEvent
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
    override val nextPhase = { if(!hasEnoughPlayers(game)) WaitingPhase(game, gameOptions, textFormatter) else GameCountdownPhase(game, gameOptions, textFormatter) }
    private val notifySound = Sound.sound(SoundEvent.BLOCK_NOTE_BLOCK_HAT, Sound.Source.NEUTRAL, 10F, 1F)
    private lateinit var countdownTask: CountdownRunnable

    override fun start() {
        registerListeners()
        startCountdown()
    }

    private fun registerListeners() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostLeaveEvent::class.java)
            .filter(GameFilter(game))) { !hasEnoughPlayers(it.game) }
    }

    private fun hasEnoughPlayers(game: Game<out GamePlayer>): Boolean = game.gamePlayers.size >= gameOptions.minPlayers

    private fun startCountdown() {
        countdownTask = Countdown.schedule(
            initialTime = gameOptions.countdownTime,
            duration = Duration.ofSeconds(1),
            afterIncrementAction = {
                if(it % 10 == 0 || it <= 5)
                    notifyCountdown(it)
            },
            endingAction = { switchNextPhase() }
        )
    }

    private fun notifyCountdown(time: Int) {
        game.sendActionBar(MiniMessage.get().parse(textFormatter.formatMessage("The game will start in ${textFormatter.formatNumber(time)} seconds!")))
        game.playSound(notifySound, Sound.Emitter.self())
    }

    override fun end() {
        stopCountdown()

        if(_nextPhase is GameCountdownPhase) {
            switchNextPhaseEventNode()
        }
    }

    private fun stopCountdown() {
        countdownTask.cancel()
    }
}