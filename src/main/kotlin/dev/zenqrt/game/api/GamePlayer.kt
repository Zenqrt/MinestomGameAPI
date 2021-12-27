package dev.zenqrt.game.api

import java.util.*

open class GamePlayer(open val uuid: UUID = UUID.randomUUID(), var currentGame: Game<out GamePlayer>? = null)