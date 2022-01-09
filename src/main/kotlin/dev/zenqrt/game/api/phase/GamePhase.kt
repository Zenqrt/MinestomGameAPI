package dev.zenqrt.game.api.phase

import dev.zenqrt.game.api.phase.trait.PhaseTrait
import net.minestom.server.MinecraftServer
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode

abstract class GamePhase(name: String, open val eventNode: EventNode<Event> = EventNode.all(name), open val phaseChangeEventNode: EventNode<Event> = EventNode.all("$name-phase_change")) {
    open val nextPhase: () -> GamePhase? = { null }
    private val traits = mutableListOf<PhaseTrait>()
    private val globalEventNode = MinecraftServer.getGlobalEventHandler()
    private var _nextPhase: GamePhase? = null

    abstract fun start()
    abstract fun end()

    fun switchNextPhase() {
        _nextPhase = nextPhase()
        end()
        _nextPhase?.start()
    }

    fun switchNextPhaseEventNode() {
        _nextPhase?.addPhaseEventNode()
        removePhaseEventNode()
    }

    fun switchNextPhaseChangeEventNode() {
        _nextPhase?.addPhaseChangeEventNode()
        removePhaseChangeEventNode()
    }

    fun switchAllNextPhaseEventNodes() {
        _nextPhase?.addAllEventNodes()
        removeAllEventNodes()
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

    fun addPhaseChangeEventNode() {
        globalEventNode.addChild(phaseChangeEventNode)
    }

    fun addAllEventNodes() {
        addPhaseEventNode()
        addPhaseChangeEventNode()
    }

    fun removePhaseEventNode() {
        globalEventNode.removeChild(eventNode)
    }

    fun removePhaseChangeEventNode() {
        globalEventNode.removeChild(phaseChangeEventNode)
    }

    fun removeAllEventNodes() {
        removePhaseEventNode()
        removePhaseChangeEventNode()
    }


    inline fun <reified T : Event> listenPhaseChangeCondition(eventBuilder: EventListener.Builder<T>, crossinline condition: (T) -> Boolean) {
        phaseChangeEventNode.addListener(eventBuilder
            .filter { condition(it) }
            .handler { switchNextPhase() }
            .build())
    }
}