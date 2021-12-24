package dev.zenqrt.game.api.event

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.event.trait.GameEvent
import dev.zenqrt.game.api.event.trait.GamePlayerEvent
import net.minestom.server.entity.Player

class GamePlayerPostJoinEvent(override val game: Game,
                              override val gamePlayer: GamePlayer,
                              private val playerEntity: Player) : GameEvent, GamePlayerEvent {

    override fun getPlayer(): Player = playerEntity
}