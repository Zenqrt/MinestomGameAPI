package dev.zenqrt.game.christmas.game

import dev.zenqrt.game.api.GamePlayer
import java.util.*

data class ChristmasGamePlayer(override val uuid: UUID, val toysBuilt: Int) : GamePlayer(uuid), Comparable<ChristmasGamePlayer> {
    override fun compareTo(other: ChristmasGamePlayer): Int = when {
        toysBuilt < other.toysBuilt -> -1
        toysBuilt == other.toysBuilt -> 0
        else -> 1
    }
}