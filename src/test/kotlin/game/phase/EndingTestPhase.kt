package game.phase

import dev.zenqrt.game.event.filter.GameEntityPlayerFilter
import dev.zenqrt.game.event.filter.GamePlayerFilter
import dev.zenqrt.game.phase.GamePhase
import game.TestGame
import net.minestom.server.event.EventListener
import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.event.entity.EntityDamageEvent
import net.minestom.server.event.entity.EntityDeathEvent
import net.minestom.server.event.player.PlayerChatEvent
import net.minestom.server.event.player.PlayerStartSneakingEvent
import net.minestom.server.event.player.PlayerStartSprintingEvent

class EndingTestPhase(private val game: TestGame) : GamePhase("ending") {

    override fun start() {
        eventNode.addListener(listenPhaseChangeCondition<EntityDeathEvent> { true }
            .filter(GameEntityPlayerFilter(game))
            .build())

        eventNode.addListener(EventListener.builder(EntityDamageEvent::class.java)
            .filter(GameEntityPlayerFilter(game))
            .handler { it.entity.isGlowing = true }
            .build())
    }

    override fun end() {
        game.gamePlayers.clear()
    }
}