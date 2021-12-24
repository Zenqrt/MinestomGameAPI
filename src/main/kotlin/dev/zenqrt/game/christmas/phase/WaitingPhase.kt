package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.api.phase.phase.MaxPlayersEventTrait
import dev.zenqrt.game.christmas.game.GameOptions
import net.minestom.server.event.EventListener

class WaitingPhase(private val game: Game, private val gameOptions: GameOptions) : GamePhase("waiting") {

    override val nextPhase = { CountdownPhase(game, gameOptions) }

    override fun start() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostJoinEvent::class.java)
            .filter(GameFilter(game))) { it.game.gamePlayers.size >= gameOptions.minPlayers }

        addTrait(MaxPlayersEventTrait(eventNode, EventListener.builder(GamePlayerJoinEvent::class.java), game, gameOptions.maxPlayers))
    }

    override fun end() {
    }
}