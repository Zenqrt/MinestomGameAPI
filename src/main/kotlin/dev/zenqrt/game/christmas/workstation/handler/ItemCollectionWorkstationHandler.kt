package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.utils.hasEmptySlot
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import net.minestom.server.sound.SoundEvent

class ItemCollectionWorkstationHandler(private val itemStack: ItemStack) : WorkstationHandler {
    override fun useStation(player: Player) {
        giveItemStack(player)
    }

    private fun giveItemStack(player: Player) {
        if(!player.inventory.hasEmptySlot()) {
            player.sendMessage(Component.text("You do not have enough space in your inventory!", NamedTextColor.RED))
        } else {
            player.inventory.addItemStack(itemStack)
            player.playSound(COLLECT_SOUND, Sound.Emitter.self())
        }
    }

    companion object {
        val COLLECT_SOUND = Sound.sound(SoundEvent.ENTITY_ITEM_PICKUP, Sound.Source.MASTER, 10F, 1F)
    }
}