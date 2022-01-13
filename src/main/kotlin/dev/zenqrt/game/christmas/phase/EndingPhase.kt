package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.leaderboard.ChristmasLeaderboardCalculator
import dev.zenqrt.game.christmas.phase.trait.DisplayLeaderboardPhaseTrait
import dev.zenqrt.game.christmas.timer.Countdown
import dev.zenqrt.game.christmas.timer.CountdownRunnable
import dev.zenqrt.game.christmas.utils.teleport
import dev.zenqrt.game.server.MinestomServer
import java.time.Duration

class EndingPhase(private val game: ChristmasGame, private val textFormatter: ChristmasTextFormatter) : GamePhase("ending") {
    lateinit var countdownTask: CountdownRunnable

    override fun start() {
        addTrait(DisplayLeaderboardPhaseTrait(game, textFormatter, ChristmasLeaderboardCalculator))
        startCountdown()
    }

    private fun startCountdown() {
        countdownTask = Countdown.schedule(
            initialTime = 10,
            duration = Duration.ofSeconds(1),
            endingAction = { switchNextPhase() }
        )
    }

    override fun end() {
        countdownTask.cancel()
        removePhaseEventNode()
        endTraits()
        teleportPlayersToLobby()
    }

    private fun teleportPlayersToLobby() {
        game.broadcast { it.teleport(MinestomServer.instanceContainer, MinestomServer.world.spawnPos) }
    }
}