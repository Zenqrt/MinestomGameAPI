package dev.zenqrt.game

import dev.zenqrt.game.phase.GamePhase

abstract class Game(private val gamePlayerHandler: GamePlayerHandler) : GamePlayerHandler by gamePlayerHandler {
    abstract val startingPhase: GamePhase
}