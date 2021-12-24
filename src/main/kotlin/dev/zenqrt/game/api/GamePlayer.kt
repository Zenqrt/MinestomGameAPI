package dev.zenqrt.game.api

import java.util.*

open class GamePlayer(val uuid: UUID = UUID.randomUUID(), var currentGame: Game? = null)