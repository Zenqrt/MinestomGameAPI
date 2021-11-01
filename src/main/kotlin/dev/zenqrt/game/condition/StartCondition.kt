package dev.zenqrt.game.condition

import dev.zenqrt.game.GameOptions

interface StartCondition {
    fun canStart(options: GameOptions): Boolean
}