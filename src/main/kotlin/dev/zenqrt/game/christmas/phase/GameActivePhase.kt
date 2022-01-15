package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.event.GamePlayerPostLeaveEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.GameOptions
import dev.zenqrt.game.christmas.phase.trait.GameTimerBossBarPhaseTrait
import dev.zenqrt.game.christmas.phase.trait.WorkstationPhaseTrait
import net.minestom.server.event.EventListener

class GameActivePhase(private val game: ChristmasGame, private val gameOptions: GameOptions, private val textFormatter: ChristmasTextFormatter) : GamePhase("active") {
    override val nextPhase = { EndingPhase(game, textFormatter) }

    override fun start() {
        if(attemptForceEnd()) return

        registerListeners()
        registerTraits()

        setupInstance()
    }

    private fun registerListeners() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostLeaveEvent::class.java)
            .filter(GameFilter(game))) { shouldForceEnd() }
    }

    private fun registerTraits() {
        addTrait(GameTimerBossBarPhaseTrait(game, this, gameOptions.gameTime))
        addTrait(WorkstationPhaseTrait(eventNode, game))
    }

    private fun shouldForceEnd(): Boolean = game.gamePlayers.size > 1

    private fun attemptForceEnd(): Boolean {
        if(shouldForceEnd()) {
            switchNextPhase()
            return true
        }
        return false
    }

    private fun setupInstance() {
        val instance = game.instance
        instance.timeRate = 4
        instance.time = 0
    }

    override fun end() {
        switchNextPhaseEventNode()
        endTraits()
    }
}