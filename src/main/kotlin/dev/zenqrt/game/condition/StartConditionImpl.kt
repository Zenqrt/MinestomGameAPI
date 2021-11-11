package dev.zenqrt.game.condition

import dev.zenqrt.game.GameOptions
import dev.zenqrt.game.GamePlayer

class StartConditionImpl(private val gameOptions: GameOptions,
                         private val players: List<GamePlayer>) : StartCondition {

    override fun canStart(): Boolean = players.size >= gameOptions.minPlayers
}