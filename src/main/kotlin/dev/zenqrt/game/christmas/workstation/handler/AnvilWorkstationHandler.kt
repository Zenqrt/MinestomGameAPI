package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.recipe.SingleRecipe
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode

class AnvilWorkstationHandler(eventNode: EventNode<Event>) : SingleRecipeWorkstationHandler("Anvil", eventNode) {
    override val recipes = listOf<SingleRecipe>()
}