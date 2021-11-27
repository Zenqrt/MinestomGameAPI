package dev.zenqrt.game

import dev.zenqrt.game.handler.GamePlayerHandler

open class Game(private val gamePlayerHandler: GamePlayerHandler,
) : GamePlayerHandler by gamePlayerHandler {

    internal open fun init() {
    }
}

data class GameOptions(val maxPlayers: Int, val minPlayers: Int)