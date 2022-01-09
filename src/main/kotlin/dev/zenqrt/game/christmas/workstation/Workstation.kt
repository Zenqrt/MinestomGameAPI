package dev.zenqrt.game.christmas.workstation

import dev.zenqrt.game.christmas.workstation.handler.WorkstationHandler
import dev.zenqrt.game.christmas.workstation.handler.impl.WorkstationHandlerImpl
import kotlinx.serialization.Serializable
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityType

data class Workstation(val interactionBox: WorkstationInteractionBox, val handler: WorkstationHandler = WorkstationHandlerImpl())

@Serializable
data class WorkstationInteractionBox(val minX: Double, val minY: Double, val minZ: Double, val maxX: Double, val maxY: Double, val maxZ: Double) {
    fun createInteractionEntity(): Entity = Entity(EntityType.ARMOR_STAND).also {
        it.setBoundingBox(maxX - minX, maxY - minY, maxZ - minZ)
    }
}