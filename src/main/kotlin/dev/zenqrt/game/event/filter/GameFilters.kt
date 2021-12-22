package dev.zenqrt.game.event.filter

import dev.zenqrt.game.Game
import dev.zenqrt.game.event.trait.GameEvent
import net.minestom.server.entity.Player
import net.minestom.server.event.trait.EntityEvent
import net.minestom.server.event.trait.PlayerEvent
import java.util.function.Predicate

class GameFilter<T : GameEvent>(private val game: Game) : Predicate<T> {
    override fun test(t: T): Boolean = t.game == game
}

class GamePlayerFilter<T : PlayerEvent>(private val game: Game) : Predicate<T> {
    override fun test(t: T): Boolean = game.gamePlayers.containsKey(t.player)
}

class GameEntityPlayerFilter<T : EntityEvent>(private val game: Game) : Predicate<T> {
    override fun test(t: T): Boolean = if(t.entity !is Player) false else game.gamePlayers.containsKey(t.entity as Player)
}