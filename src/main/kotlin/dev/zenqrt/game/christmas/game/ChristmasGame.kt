package dev.zenqrt.game.christmas.game

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayerHandlerImpl
import dev.zenqrt.game.christmas.phase.WaitingPhase

class ChristmasGame : Game(GamePlayerHandlerImpl()) {
    override val startingPhase = WaitingPhase(this, GameOptions(4, 8, 30))
}