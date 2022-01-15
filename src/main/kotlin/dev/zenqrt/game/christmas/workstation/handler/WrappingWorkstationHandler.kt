package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.item.toy.material.wrapping.WrappedPresentItem
import dev.zenqrt.game.christmas.item.toy.material.wrapping.WrappingColors
import dev.zenqrt.game.christmas.utils.wrappable
import net.minestom.server.entity.Player
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.inventory.InventoryItemChangeEvent
import net.minestom.server.event.trait.InventoryEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import world.cepi.kstom.adventure.plainText
import world.cepi.kstom.event.listen

class WrappingWorkstationHandler : WorkstationHandler {
    private val inventoryUI: Inventory
        get() = Inventory(InventoryType.GRINDSTONE, "Wrapping Station")

    override fun useStation(player: Player) {
        player.openInventory(inventoryUI)
    }

    private fun createEventNode(): EventNode<InventoryEvent> = EventNode.type("wrapping_station", EventFilter.INVENTORY).also {
        it.listen<InventoryItemChangeEvent> {
            handler {
                val inventory = this.inventory ?: return@handler
                if(this.slot == 0) {
                    if(!this.newItem.wrappable) {
                        inventory.setItemStack(this.slot, this.previousItem)
                    } else {
                        inventory.setItemStack(1, WrappedPresentItem(this.newItem.displayName!!.plainText(), WrappingColors.GREEN).createItemStack())
                    }
                }
            }
        }
    }
}