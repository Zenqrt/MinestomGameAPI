package dev.zenqrt.game.christmas.workstation.handler

import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

class PlasticMolderWorkstationHandler : WorkstationHandler {
    override fun useStation(player: Player) {
        player.openInventory(getInventoryUI())
    }

    private fun getInventoryUI(): Inventory = Inventory(InventoryType.STONE_CUTTER, "Plastic Molder")
}