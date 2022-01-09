package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.event.GamePlayerPostLeaveEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.GameOptions
import dev.zenqrt.game.christmas.phase.trait.GameTimerBossBarPhaseTrait
import dev.zenqrt.game.christmas.phase.trait.WorkstationPhaseTrait
import net.minestom.server.event.EventListener

class GameActivePhase(private val game: ChristmasGame, private val gameOptions: GameOptions) : GamePhase("active") {
    override val nextPhase = { EndingPhase() }

    override fun start() {
        if(attemptForceEnd()) return

        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostLeaveEvent::class.java)
            .filter(GameFilter(game))) { shouldForceEnd() }
        registerTraits()
    }

    private fun registerTraits() {
        addTrait(GameTimerBossBarPhaseTrait(this, gameOptions.gameTime))
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

    override fun end() {
        switchAllNextPhaseEventNodes()
        endTraits()
    }
}