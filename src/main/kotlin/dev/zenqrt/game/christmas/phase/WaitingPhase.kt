package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.api.phase.trait.MaxPlayerLimitCancelEventTrait
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.GameOptions
import dev.zenqrt.game.christmas.phase.trait.PlayerCountBossBarPhaseTrait
import dev.zenqrt.game.christmas.phase.trait.TeleportToSpawnPhaseTrait
import net.minestom.server.event.EventListener

class WaitingPhase(private val game: ChristmasGame, private val gameOptions: GameOptions, private val textFormatter: ChristmasTextFormatter) : GamePhase("waiting") {
    override val nextPhase = { CountdownPhase(eventNode, game, gameOptions, textFormatter) }

    init {
        addAllEventNodes()
    }

    override fun start() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostJoinEvent::class.java)
            .filter(GameFilter(game))) { it.game.gamePlayers.size >= gameOptions.minPlayers }
        registerTraits()
    }

    private fun registerTraits() {
        addTrait(MaxPlayerLimitCancelEventTrait(eventNode, EventListener.builder(GamePlayerJoinEvent::class.java), game, gameOptions.maxPlayers))
        addTrait(PlayerCountBossBarPhaseTrait(game, eventNode, textFormatter, gameOptions.maxPlayers))
        addTrait(TeleportToSpawnPhaseTrait(eventNode, game, game.instance, game.spawnPos))
    }

    override fun end() {
        switchNextPhaseChangeEventNode()
    }
}