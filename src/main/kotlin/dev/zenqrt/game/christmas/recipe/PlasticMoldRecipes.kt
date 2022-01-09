package dev.zenqrt.game.christmas.recipe

import dev.zenqrt.game.christmas.item.toy.material.plastic.PlasticItem
import dev.zenqrt.game.christmas.item.toy.material.plastic.WheelPlasticItem
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import net.minestom.server.network.packet.server.play.DeclareRecipesPacket
import net.minestom.server.recipe.StonecutterRecipe
import net.minestom.server.tag.Tag

open class PlasticMoldRecipe(recipeId: String, ingredient: DeclareRecipesPacket.Ingredient, result: ItemStack) : StonecutterRecipe(recipeId, "plastic_mold", ingredient, result) {
    override fun shouldShow(player: Player): Boolean = if(player.openInventory != null) player.openInventory!!.hasTag(Tag.String("plastic_molder_station")) else false
}

class WheelPlasticMoldRecipe : PlasticMoldRecipe("wheel", DeclareRecipesPacket.Ingredient(listOf(PlasticItem().createItemStack())), WheelPlasticItem().createItemStack())