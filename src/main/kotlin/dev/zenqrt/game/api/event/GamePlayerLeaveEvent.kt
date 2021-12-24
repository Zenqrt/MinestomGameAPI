package dev.zenqrt.game.api.event

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.event.trait.GameEvent
import dev.zenqrt.game.api.event.trait.GamePlayerEvent
import net.minestom.server.entity.Player
import net.minestom.server.event.trait.CancellableEvent

class GamePlayerLeaveEvent(override val game: Game, override val gamePlayer: GamePlayer, private val playerEntity: Player) : GameEvent,
    GamePlayerEvent, CancellableEvent {
    private var cancel = false

    override fun isCancelled(): Boolean = cancel

    override fun setCancelled(cancel: Boolean) {
        this.cancel = cancel
    }

    override fun getPlayer(): Player = playerEntity
}