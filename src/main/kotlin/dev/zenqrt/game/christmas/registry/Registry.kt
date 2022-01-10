package dev.zenqrt.game.christmas.registry

import dev.zenqrt.game.christmas.recipe.WheelPlasticMoldRecipe
import net.minestom.server.MinecraftServer
import world.cepi.kstom.Manager

object Registry {
    fun registerAll() {
        registerRecipes()
    }

    private fun registerRecipes() {
        val manager = Manager.recipe

        manager.addRecipe(WheelPlasticMoldRecipe())
    }
}