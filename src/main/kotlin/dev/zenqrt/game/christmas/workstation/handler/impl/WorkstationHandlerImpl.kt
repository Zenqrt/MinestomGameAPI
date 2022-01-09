package dev.zenqrt.game.christmas.workstation.handler.impl

import dev.zenqrt.game.christmas.workstation.handler.WorkstationHandler
import net.minestom.server.entity.Player

internal class WorkstationHandlerImpl : WorkstationHandler {
    override fun useStation(player: Player) {}
    override fun equals(other: Any?): Boolean = true
    override fun hashCode(): Int = 1
}
