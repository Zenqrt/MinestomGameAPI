package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.Items
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack

class SantaSleighWorkstationHandler(private val game: ChristmasGame) : WorkstationHandler {
    override fun useStation(player: Player) {
        if(!isWrappedPresent(player.itemInMainHand)) return
        consumeItemInHand(player)
        incrementToysBuilt(player)
    }

    private fun isWrappedPresent(itemStack: ItemStack): Boolean = itemStack.getTag(Item.ID_TAG) == Items.WRAPPED_PRESENT.id

    private fun consumeItemInHand(player: Player) {
        player.itemInMainHand = player.itemInMainHand.withAmount { it - 1 }
    }
    
    private fun incrementToysBuilt(player: Player) {
        val gamePlayer = game.gamePlayers[player]
        gamePlayer?.copy(toysBuilt = gamePlayer.toysBuilt + 1)?.let { game.updatePlayer(it, player) }
    }
}