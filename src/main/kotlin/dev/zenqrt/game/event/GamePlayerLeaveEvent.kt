package dev.zenqrt.game.event

import dev.zenqrt.game.Game
import dev.zenqrt.game.GamePlayer
import dev.zenqrt.game.event.trait.GameEvent
import dev.zenqrt.game.event.trait.GamePlayerEvent
import net.minestom.server.event.trait.CancellableEvent

class GamePlayerLeaveEvent(override val game: Game, override val gamePlayer: GamePlayer) : GameEvent, GamePlayerEvent, CancellableEvent {
    private var cancel = false

    override fun isCancelled(): Boolean = cancel

    override fun setCancelled(cancel: Boolean) {
        this.cancel = cancel
    }
}