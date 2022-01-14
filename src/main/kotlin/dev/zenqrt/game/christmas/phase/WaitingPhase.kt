package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.api.phase.trait.MaxPlayerLimitCancelEventTrait
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.GameOptions
import dev.zenqrt.game.christmas.phase.trait.PlayerActivityPhaseTrait
import dev.zenqrt.game.christmas.phase.trait.SnowEffectPhaseTrait
import dev.zenqrt.game.christmas.phase.trait.TeleportToSpawnPhaseTrait
import net.minestom.server.event.EventListener

class WaitingPhase(private val game: ChristmasGame, private val gameOptions: GameOptions, private val textFormatter: ChristmasTextFormatter) : GamePhase("waiting") {
    override val nextPhase = { CountdownPhase(eventNode, game, gameOptions, textFormatter) }

    init {
        addPhaseEventNode()
    }

    override fun start() {
        registerListeners()
        registerTraits()
    }

    private fun registerListeners() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostJoinEvent::class.java)
            .filter(GameFilter(game))) { it.game.gamePlayers.size >= gameOptions.minPlayers }
    }

    private fun registerTraits() {
        addTrait(MaxPlayerLimitCancelEventTrait(eventNode, EventListener.builder(GamePlayerJoinEvent::class.java), game, gameOptions.maxPlayers))
        addTrait(PlayerActivityPhaseTrait(game, eventNode, textFormatter, gameOptions.maxPlayers))
        addTrait(SnowEffectPhaseTrait(eventNode, game))
        addTrait(TeleportToSpawnPhaseTrait(eventNode, game, game.instance, game.christmasMapWorld.spawnPos))
    }

    override fun end() {

    }
}