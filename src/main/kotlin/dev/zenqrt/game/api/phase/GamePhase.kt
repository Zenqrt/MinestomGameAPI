package dev.zenqrt.game.api.phase

import dev.zenqrt.game.api.phase.trait.PhaseTrait
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import world.cepi.kstom.Manager

abstract class GamePhase(name: String, open val eventNode: EventNode<Event> = EventNode.all(name), open val phaseChangeEventNode: EventNode<Event> = EventNode.all("$name-phase_change")) {
    open val nextPhase: () -> GamePhase? = { null }
    private val traits = mutableListOf<PhaseTrait>()
    private val globalEventNode = Manager.globalEvent
    private var _nextPhase: GamePhase? = null
    var active = false

    protected abstract fun start()
    protected abstract fun end()

    fun startPhase() {
        start()
        globalEventNode.addChild(phaseChangeEventNode)
        active = true
    }

    fun endPhase() {
        end()
        globalEventNode.removeChild(phaseChangeEventNode)
        active = false
    }

    fun switchNextPhase() {
        _nextPhase = nextPhase()
        endPhase()
        _nextPhase?.startPhase()
    }

    fun switchNextPhaseEventNode() {
        _nextPhase?.addPhaseEventNode()
        removePhaseEventNode()
    }

    fun addTrait(trait: PhaseTrait)  {
        traits.add(trait)
        trait.handleTrait()
    }

    fun removeTrait(trait: PhaseTrait) {
        traits.remove(trait)
        trait.endTrait()
    }

    fun endTraits() {
        traits.forEach { it.endTrait() }
    }

    fun addPhaseEventNode() {
        globalEventNode.addChild(eventNode)
    }

    fun removePhaseEventNode() {
        globalEventNode.removeChild(eventNode)
    }

    inline fun <reified T : Event> listenPhaseChangeCondition(eventBuilder: EventListener.Builder<T>, crossinline condition: (T) -> Boolean) {
        phaseChangeEventNode.addListener(eventBuilder
            .filter { condition(it) }
            .handler { switchNextPhase() }
            .build())
    }
}