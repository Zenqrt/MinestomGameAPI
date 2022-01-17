package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.recipe.CustomRecipes
import dev.zenqrt.game.christmas.recipe.PlasticRecipe
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode

class PlasticMolderWorkstationHandler(eventNode: EventNode<Event>) : SingleRecipeWorkstationHandler("Plastic Molding Station", eventNode) {
    override val recipes = listOf<PlasticRecipe>(
        CustomRecipes.PLASTIC_WHEEL
    )
}