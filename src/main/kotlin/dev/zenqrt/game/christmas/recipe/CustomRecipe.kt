package dev.zenqrt.game.christmas.recipe

import dev.zenqrt.game.christmas.registry.RecipeRegistryService
import net.minestom.server.item.ItemStack

interface CustomRecipe {
    val id: String
}

interface SingleRecipe : CustomRecipe {
    val ingredient: ItemStack
    val result: ItemStack
}

interface DoubleRecipe : CustomRecipe {
    val firstIngredient: ItemStack
    val secondIngredient: ItemStack
    val result: ItemStack
}

object CustomRecipes {
    val PLASTIC_WHEEL = register(WheelPlasticRecipe())

    private fun <T : CustomRecipe> register(recipe: T): T = recipe.also { RecipeRegistryService.register(it.id, it) }
}