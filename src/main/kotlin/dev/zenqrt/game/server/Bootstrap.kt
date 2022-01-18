package dev.zenqrt.game.server

import dev.zenqrt.game.christmas.commands.GameCommand
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.workstation.Workstation
import dev.zenqrt.game.christmas.workstation.WorkstationInteractionBox
import dev.zenqrt.game.christmas.workstation.handler.*
import dev.zenqrt.game.christmas.workstation.handler.impl.WorkstationHandlerImpl
import dev.zenqrt.game.christmas.world.block.handlers.BannerBlockHandler
import dev.zenqrt.game.christmas.world.block.handlers.SkullBlockHandler
import dev.zenqrt.game.christmas.world.worlds.ChristmasMapWorld
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.GameMode
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.extras.MojangAuth
import net.minestom.server.extras.optifine.OptifineSupport
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.utils.NamespaceID
import net.minestom.server.world.biomes.Biome
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly
import java.io.File

object MinestomServer {
    lateinit var instanceContainer: InstanceContainer
    lateinit var world: ChristmasMapWorld

    @JvmStatic
    fun main(args: Array<String>) {
        val minecraftServer = MinecraftServer.init()

        registerAll()

        world = ChristmasMapWorld()
        instanceContainer = world.createInstanceContainer()

        Manager.globalEvent.listenOnly<PlayerLoginEvent> {
            this.setSpawningInstance(instanceContainer)
            this.player.respawnPoint = world.spawnPos
            this.player.gameMode = GameMode.ADVENTURE
//            this.player.isAllowFlying = true
//            this.player.isFlying = true
        }

        minecraftServer.start("0.0.0.0", 25565)
    }

    private fun registerAll() {
        MojangAuth.init()
        OptifineSupport.enable()

        GameCommand.register()

        registerWorlds()
        registerBlockHandlers()
    }

    fun registerWorlds() {
        Manager.dimensionType.addDimension(ChristmasMapWorld.DIMENSION_TYPE)
    }

    private fun registerBlockHandlers() {
        val manager = Manager.block

        manager.registerHandler("minecraft:banner") { BannerBlockHandler() }
        manager.registerHandler("minecraft:skull") { SkullBlockHandler() }
    }


}