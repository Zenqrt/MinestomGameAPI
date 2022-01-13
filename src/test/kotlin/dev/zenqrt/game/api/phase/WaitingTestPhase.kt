package dev.zenqrt.game.api.phase

import dev.zenqrt.game.api.TestGame
import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import dev.zenqrt.game.api.event.GamePlayerLeaveEvent
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.trait.MaxPlayerLimitCancelEventTrait
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import world.cepi.kstom.event.listen

abstract class AbstractWaitingTestPhase(protected val game: TestGame) : GamePhase("waiting") {
    override val nextPhase = { EndingTestPhase(game) }

    init {
        addAllEventNodes()
    }

    override fun start() {
        WaitingTestPhaseUtils.addPhaseChangeConditionListener(this, game)
        WaitingTestPhaseUtils.addTraits(this, game)
        WaitingTestPhaseUtils.addEventListeners(eventNode, game)
    }
}

class WaitingTestPhase(game: TestGame) : AbstractWaitingTestPhase(game) {
    override fun end() {
        WaitingTestPhaseUtils.executeEnding(game)
        switchAllNextPhaseEventNodes()
        endTraits()
    }
}

class WaitingTestPhaseOnlySwitchEventNode(game: TestGame) : AbstractWaitingTestPhase(game) {
    override fun end() {
        println("ENDING")
        WaitingTestPhaseUtils.executeEnding(game)
        switchNextPhaseEventNode()
        endTraits()
    }
}

class WaitingTestPhaseOnlySwitchPhaseChangeEventNode(game: TestGame) : AbstractWaitingTestPhase(game) {
    override fun end() {
        WaitingTestPhaseUtils.executeEnding(game)
        switchNextPhaseChangeEventNode()
        endTraits()
    }
}

class WaitingTestPhaseKeepTraits(game: TestGame) : AbstractWaitingTestPhase(game) {
    override fun end() {
        WaitingTestPhaseUtils.executeEnding(game)
        switchAllNextPhaseEventNodes()
    }
}

private object WaitingTestPhaseUtils {
    const val maxPlayers = 4

    fun addPhaseChangeConditionListener(phase: GamePhase, game: TestGame) {
        phase.listenPhaseChangeCondition(EventListener.builder(GamePlayerPostJoinEvent::class.java)
            .filter(GameFilter(game))) {
            println("EVENT")
            it.game.gamePlayers.size >= maxPlayers
        }
    }

    fun addEventListeners(eventNode: EventNode<Event>, game: TestGame) {
        eventNode.listen<GamePlayerJoinEvent> {
            filters += GameFilter(game)
            handler { this.player.health = 5F }
        }

        eventNode.listen<GamePlayerLeaveEvent> {
            filters += GameFilter(game)
            handler { this.player.heal() }
        }
    }

    fun addTraits(phase: GamePhase, game: TestGame) {
        phase.addTrait(MaxPlayerLimitCancelEventTrait(phase.eventNode, EventListener.builder(GamePlayerJoinEvent::class.java), game, maxPlayers))
    }

    fun executeEnding(game: TestGame) {
        game.gamePlayers.forEach { it.key.additionalHearts = 10F }
    }
}