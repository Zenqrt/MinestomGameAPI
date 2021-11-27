package dev.zenqrt.game.phase

import net.minestom.server.MinecraftServer
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import java.util.function.Predicate

abstract class GamePhase(name: String, private val eventNode: EventNode<Event> = EventNode.all(name)): EventNode<Event> by eventNode {
    open val nextPhase: () -> GamePhase? = { null }

    init {
        MinecraftServer.getGlobalEventHandler().addChild(eventNode)
    }

    abstract fun start()
    abstract fun end()

    fun cleanup() {
        end()
        nextPhase()?.start()
        MinecraftServer.getGlobalEventHandler().removeChild(eventNode)
    }

    inline fun <reified T : Event> listenPhaseChangeCondition(crossinline condition: (T) -> Boolean): EventListener.Builder<T> = EventListener.builder(T::class.java)
        .handler {
            if(condition(it)) {
                cleanup()
            }
        }
}