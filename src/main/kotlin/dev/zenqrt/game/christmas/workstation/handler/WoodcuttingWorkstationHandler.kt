package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.recipe.SledWoodRecipe
import net.kyori.adventure.sound.Sound
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.sound.SoundEvent

class WoodcuttingWorkstationHandler(eventNode: EventNode<Event>) : SingleRecipeWorkstationHandler("Woodcutting Station", eventNode) {
    override val recipes = listOf(
        SledWoodRecipe()
    )
    override val useSound = Sound.sound(SoundEvent.ITEM_AXE_STRIP, Sound.Source.MASTER, 10F, 0F)
}