package dev.zenqrt.game.christmas.workstation.handler

import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

class WoodcuttingWorkstationHandler : WorkstationHandler {
    private val inventoryUI: Inventory
        get() = Inventory(InventoryType.STONE_CUTTER, "Woodcutting Station")

    override fun useStation(player: Player) {
        unlockRecipes(player)
        player.openInventory(inventoryUI)
    }

    private fun unlockRecipes(player: Player) {

    }
}