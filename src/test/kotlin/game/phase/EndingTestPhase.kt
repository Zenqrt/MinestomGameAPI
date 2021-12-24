package game.phase

import dev.zenqrt.game.api.event.filter.GameEntityPlayerFilter
import dev.zenqrt.game.api.phase.GamePhase
import game.TestGame
import net.minestom.server.event.EventListener
import net.minestom.server.event.entity.EntityDamageEvent
import net.minestom.server.event.entity.EntityDeathEvent

class EndingTestPhase(private val game: TestGame) : GamePhase("ending") {

    override fun start() {
        listenPhaseChangeCondition(EventListener.builder(EntityDeathEvent::class.java)
            .filter(GameEntityPlayerFilter(game))) { true }

        eventNode.addListener(EventListener.builder(EntityDamageEvent::class.java)
            .filter(GameEntityPlayerFilter(game))
            .handler { it.entity.isGlowing = true }
            .build())
    }

    override fun end() {
        game.gamePlayers.clear()
    }
}