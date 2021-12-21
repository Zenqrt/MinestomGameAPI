package dev.zenqrt.game.event.filter

import dev.zenqrt.game.Game
import dev.zenqrt.game.event.trait.GameEvent
import net.minestom.server.event.trait.PlayerEvent
import java.util.function.Predicate

class GameFilter<T : GameEvent>(private val game: Game) : Predicate<T> {
    override fun test(t: T): Boolean = t.game == game
}

class GamePlayerFilter<T : PlayerEvent>(private val game: Game) : Predicate<T> {
    override fun test(t: T): Boolean {
        val test = game.gamePlayers.containsKey(t.player)
        println(test)
        return test
    }
}