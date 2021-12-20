package dev.zenqrt.game.phase

import net.minestom.server.MinecraftServer
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode

abstract class GamePhase(name: String) {
    open val nextPhase: () -> GamePhase? = { null }
    val eventNode: EventNode<Event> = EventNode.all(name)

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

    inline fun <reified T : Event> listenPhaseChangeCondition(crossinline condition: (T) -> Boolean): EventListener.Builder<T> = EventListener.builder(T::class.java)
        .handler {
            if(condition(it)) {
                changePhase()
            }
        }
}