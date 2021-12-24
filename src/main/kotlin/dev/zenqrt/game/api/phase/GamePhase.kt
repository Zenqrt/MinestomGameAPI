package dev.zenqrt.game.api.phase

import dev.zenqrt.game.api.phase.trait.PhaseTrait
import net.minestom.server.MinecraftServer
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode

abstract class GamePhase(name: String, val eventNode: EventNode<Event> = EventNode.all(name)) {
    open val nextPhase: () -> GamePhase? = { null }

    init {
        MinecraftServer.getGlobalEventHandler().addChild(eventNode)
    }

    abstract fun start()
    abstract fun end()

    fun changePhase() {
        end()
        MinecraftServer.getGlobalEventHandler().removeChild(eventNode)
        nextPhase()?.start()
    }

    fun addTrait(trait: PhaseTrait)  {
        trait.handleTrait()
    }

    inline fun <reified T : Event> listenPhaseChangeCondition(eventBuilder: EventListener.Builder<T>, crossinline condition: (T) -> Boolean) {
        eventNode.addListener(eventBuilder
            .filter { condition(it) }
            .handler { changePhase() }
            .build())
    }
}