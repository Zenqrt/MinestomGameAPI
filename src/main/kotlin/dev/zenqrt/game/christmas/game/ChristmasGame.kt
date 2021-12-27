package dev.zenqrt.game.christmas.game

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.AbstractGamePlayerHandler
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.phase.WaitingPhase

class ChristmasGame : Game<ChristmasGamePlayer>(ChristmasGamePlayerHandler()) {
    override val startingPhase = WaitingPhase(this, GameOptions(4, 8, 30), ChristmasTextFormatter())
}