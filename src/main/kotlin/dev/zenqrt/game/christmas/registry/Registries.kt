package dev.zenqrt.game.christmas.registry

import dev.zenqrt.game.api.registry.MappedRegistryService
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.recipe.CustomRecipe
import dev.zenqrt.game.christmas.workstation.Workstation
import net.minestom.server.coordinate.Point
import net.minestom.server.coordinate.Vec
import kotlin.random.Random

object RecipeRegistryService : MappedRegistryService<String, CustomRecipe>()

object GameRegistryService : MappedRegistryService<Int, ChristmasGame>() {
    fun findAvailableId(): Int {
        val id = Random.nextInt(10000)

        return if(find(id) != null) findAvailableId() else id
    }
}

class WorkstationRegistryService : MappedRegistryService<Point, Workstation>() {
    fun register(workstation: Workstation) {
        val interactionBox = workstation.interactionBox

        for(x in interactionBox.minX.toInt()..interactionBox.maxX.toInt()) {
            for(y in interactionBox.minY.toInt()..interactionBox.maxY.toInt()) {
                for(z in interactionBox.minZ.toInt()..interactionBox.maxZ.toInt()) {
                    val pos = Vec(x.toDouble(), y.toDouble(), z.toDouble())
                    map[pos] = workstation
                }
            }
        }
    }
}