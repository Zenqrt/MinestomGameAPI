package dev.zenqrt.game.api.phase

import dev.zenqrt.game.api.TestGame
import dev.zenqrt.game.api.event.filter.GameEntityPlayerFilter
import dev.zenqrt.game.api.event.filter.GamePlayerFilter
import dev.zenqrt.game.api.phase.trait.TestPhaseTrait
import net.minestom.server.event.EventListener
import net.minestom.server.event.entity.EntityDamageEvent
import net.minestom.server.event.entity.EntityDeathEvent
import net.minestom.server.event.player.PlayerDeathEvent

class EndingTestPhase(private val game: TestGame) : GamePhase("ending") {

    override fun start() {
        listenPhaseChangeCondition(EventListener.builder(PlayerDeathEvent::class.java)
            .filter(GamePlayerFilter(game))) { true }

        eventNode.addListener(EventListener.builder(EntityDamageEvent::class.java)
            .filter(GameEntityPlayerFilter(game))
            .handler { it.entity.isGlowing = true }
            .build())
        addTrait(TestPhaseTrait(game))
    }

    override fun end() {
        removeAllEventNodes()
        endTraits()
        game.gamePlayers.clear()
    }
}