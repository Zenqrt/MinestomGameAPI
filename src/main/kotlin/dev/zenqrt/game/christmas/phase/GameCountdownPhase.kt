package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.event.filter.GamePlayerFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.api.phase.trait.CancelEventTrait
import dev.zenqrt.game.christmas.game.ChristmasGame
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.event.EventListener
import net.minestom.server.event.player.PlayerMoveEvent

class GameCountdownPhase(private val game: ChristmasGame) : GamePhase("game_countdown") {
    private val workstations = mutableListOf(Pos(0.0,0.0,0.0))

    override fun start() {
        game.broadcast { sendToWorkstation(it) }
        addTrait(CancelEventTrait(eventNode, EventListener.builder(PlayerMoveEvent::class.java)
            .filter(GamePlayerFilter(game))))
    }

    override fun end() {
    }

    fun sendToWorkstation(player: Player) {
        if(workstations.isEmpty()) return

        val firstAvailable = workstations.first()
        player.teleport(firstAvailable)
        workstations.remove(firstAvailable)
    }
}