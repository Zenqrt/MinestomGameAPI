package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.api.phase.trait.MaxPlayerLimitCancelEventTrait
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.GameOptions
import dev.zenqrt.game.christmas.phase.trait.PlayerCountBossBarPhaseTrait
import net.minestom.server.event.EventListener

class WaitingPhase(private val game: Game, private val gameOptions: GameOptions, private val messageFormatter: ChristmasTextFormatter) : GamePhase("waiting") {
    override val nextPhase = { CountdownPhase(game, gameOptions, messageFormatter) }

    override fun start() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostJoinEvent::class.java)
            .filter(GameFilter(game))) { it.game.gamePlayers.size >= gameOptions.minPlayers }

        addTrait(MaxPlayerLimitCancelEventTrait(eventNode, EventListener.builder(GamePlayerJoinEvent::class.java), game, gameOptions.maxPlayers))
        addTrait(PlayerCountBossBarPhaseTrait(game, eventNode, messageFormatter, gameOptions.maxPlayers))
    }

    override fun end() {
    }
}