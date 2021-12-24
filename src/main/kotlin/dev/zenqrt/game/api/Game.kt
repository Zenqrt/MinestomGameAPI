package dev.zenqrt.game.api

import dev.zenqrt.game.api.phase.GamePhase

abstract class Game(private val gamePlayerHandler: GamePlayerHandler) : GamePlayerHandler by gamePlayerHandler {
    abstract val startingPhase: GamePhase
}