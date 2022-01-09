package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.utils.hasEmptySlot
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack

class ItemCollectionWorkstationHandler(private val itemStack: ItemStack) : WorkstationHandler {
    override fun useStation(player: Player) {
        giveItemStack(player)
    }

    private fun giveItemStack(player: Player) {
        if(!player.inventory.hasEmptySlot()) {
            player.sendMessage(Component.text("You do not have enough space in your inventory!", NamedTextColor.RED))
        } else {
            player.inventory.addItemStack(itemStack)
        }
    }
}