package dev.zenqrt.game.condition

import dev.zenqrt.game.GameOptions

class StartConditionImpl : StartCondition {

    override fun canStart(options: GameOptions): Boolean {
        if(options.minPlayers >= options.maxPlayers / 2) {
            return true
        }
        return false
    }
}