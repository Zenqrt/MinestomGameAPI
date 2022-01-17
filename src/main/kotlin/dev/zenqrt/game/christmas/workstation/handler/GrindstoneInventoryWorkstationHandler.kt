package dev.zenqrt.game.christmas.workstation.handler

import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.trait.InventoryEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly

abstract class GrindstoneInventoryWorkstationHandler(private val name: String, private val icon: ItemStack, private val eventNode: EventNode<Event>) : WorkstationHandler {
    private val inventoryUI: Inventory
        get() = Inventory(InventoryType.GRINDSTONE, name).also { it.setItemStack(1, icon) }

    abstract fun getResult(itemStack: ItemStack): ItemStack?

    override fun useStation(player: Player) {
        val inventory = inventoryUI
        eventNode.map(createEventNode(), inventory)
        player.openInventory(inventory)
    }

    private fun createEventNode(): EventNode<InventoryEvent> = EventNode.type("grindstone_workstation", EventFilter.INVENTORY).also {
        it.listenOnly<InventoryPreClickEvent> {
            this.inventory?.let { inventory ->
                when(this.slot) {
                    0 -> { setResult(inventory, this.cursorItem) }
                    1 -> this.isCancelled = true
                }
            }
        }
    }

    private fun setResult(inventory: Inventory, itemStack: ItemStack) {
        val item = getResult(itemStack) ?: ItemStack.AIR
        inventory.setItemStack(2, item)
        Manager.scheduler.buildTask { inventory.update() }.schedule()
    }
}