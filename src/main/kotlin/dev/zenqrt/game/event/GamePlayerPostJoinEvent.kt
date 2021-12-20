package dev.zenqrt.game.event

import dev.zenqrt.game.Game
import dev.zenqrt.game.GamePlayer
import dev.zenqrt.game.event.trait.GameEvent
import dev.zenqrt.game.event.trait.GamePlayerEvent
import net.minestom.server.entity.Player

class GamePlayerPostJoinEvent(override val game: Game,
                              override val gamePlayer: GamePlayer,
                              private val playerEntity: Player) : GameEvent, GamePlayerEvent {

    override fun getPlayer(): Player = playerEntity
}