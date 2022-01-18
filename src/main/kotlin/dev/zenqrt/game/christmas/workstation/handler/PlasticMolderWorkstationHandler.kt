package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.recipe.CustomRecipes
import dev.zenqrt.game.christmas.recipe.PlasticRecipe
import net.kyori.adventure.sound.Sound
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.sound.SoundEvent

class PlasticMolderWorkstationHandler(eventNode: EventNode<Event>) : SingleRecipeWorkstationHandler("Plastic Molding Station", eventNode) {
    override val recipes = listOf<PlasticRecipe>(
        CustomRecipes.PLASTIC_WHEEL
    )

    override val useSound = Sound.sound(SoundEvent.BLOCK_GRINDSTONE_USE, Sound.Source.MASTER, 10F, 1.5F)
}