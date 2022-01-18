package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.recipe.SingleRecipe
import net.kyori.adventure.sound.Sound
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.sound.SoundEvent

class AnvilWorkstationHandler(eventNode: EventNode<Event>) : SingleRecipeWorkstationHandler("Anvil", eventNode) {
    override val recipes = listOf<SingleRecipe>()
    override val useSound = Sound.sound(SoundEvent.BLOCK_SMITHING_TABLE_USE, Sound.Source.MASTER, 10F, 1F)
}