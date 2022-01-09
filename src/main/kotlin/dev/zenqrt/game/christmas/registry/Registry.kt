package dev.zenqrt.game.christmas.registry

import dev.zenqrt.game.christmas.recipe.WheelPlasticMoldRecipe
import net.minestom.server.MinecraftServer

object Registry {
    fun registerAll() {
        registerRecipes()
    }

    private fun registerRecipes() {
        val manager = MinecraftServer.getRecipeManager()

        manager.addRecipe(WheelPlasticMoldRecipe())
    }
}