package dev.zenqrt.game.christmas.game

import dev.zenqrt.game.api.GamePlayer
import java.util.*

data class ChristmasGamePlayer(override val uuid: UUID, val toysBuilt: Int) : GamePlayer(uuid)