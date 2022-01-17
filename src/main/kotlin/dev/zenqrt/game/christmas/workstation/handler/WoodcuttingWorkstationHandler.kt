package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.recipe.SledWoodRecipe
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode

class WoodcuttingWorkstationHandler(eventNode: EventNode<Event>) : SingleRecipeWorkstationHandler("Woodcutting Station", eventNode) {
    override val recipes = listOf(
        SledWoodRecipe()
    )
}