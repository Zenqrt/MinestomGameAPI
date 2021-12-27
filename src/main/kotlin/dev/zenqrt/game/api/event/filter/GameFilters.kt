package dev.zenqrt.game.api.event.filter

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.event.trait.GameEvent
import net.minestom.server.entity.Player
import net.minestom.server.event.trait.EntityEvent
import net.minestom.server.event.trait.PlayerEvent
import java.util.function.Predicate

class GameFilter<T : GameEvent>(private val game: Game<out GamePlayer>) : Predicate<T> {
    override fun test(t: T): Boolean = t.game == game
}

class GamePlayerFilter<T : PlayerEvent>(private val game: Game<out GamePlayer>) : Predicate<T> {
    override fun test(t: T): Boolean = game.gamePlayers.containsKey(t.player)
}

class GameEntityPlayerFilter<T : EntityEvent>(private val game: Game<out GamePlayer>) : Predicate<T> {
    override fun test(t: T): Boolean = if(t.entity !is Player) false else game.gamePlayers.containsKey(t.entity as Player)
}