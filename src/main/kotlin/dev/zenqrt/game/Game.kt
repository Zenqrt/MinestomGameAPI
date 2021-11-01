package dev.zenqrt.game

import dev.zenqrt.game.condition.StartCondition
import dev.zenqrt.game.condition.StartConditionImpl
import net.minestom.server.instance.InstanceContainer

open class Game(startCondition: StartCondition = StartConditionImpl(), ): StartCondition by startCondition {
    val instance: InstanceContainer ?= null

    open fun tick() {}

}