package dev.zenqrt.game.christmas.game

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.leaderboard.ChristmasLeaderboardCalculator
import dev.zenqrt.game.christmas.leaderboard.Leaderboard
import dev.zenqrt.game.christmas.phase.WaitingPhase
import dev.zenqrt.game.christmas.registry.GameRegistryService
import dev.zenqrt.game.christmas.registry.WorkstationRegistryService
import dev.zenqrt.game.christmas.sidebar.ChristmasGameSidebarCreator
import dev.zenqrt.game.christmas.workstation.Workstation
import dev.zenqrt.game.christmas.workstation.WorkstationInteractionBox
import dev.zenqrt.game.christmas.workstation.handler.*
import dev.zenqrt.game.christmas.workstation.handler.impl.WorkstationHandlerImpl
import dev.zenqrt.game.christmas.world.worlds.ChristmasMapWorld
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.minestom.server.event.EventNode
import net.minestom.server.instance.Instance
import java.io.File

class ChristmasGame(id: Int, val gameOptions: GameOptions = GameOptions(1, 8, 30, 300, 3)) : Game<ChristmasGamePlayer>(id, ChristmasGamePlayerHandler()) {
    val textFormatter = ChristmasTextFormatter()
    override val startingPhase = WaitingPhase(this, gameOptions, textFormatter)
    val christmasMapWorld = ChristmasMapWorld()

    val instance: Instance
    val workstationRegistry = WorkstationRegistryService()

    val workstationEventNode = EventNode.all("workstation")
    val leaderboard = Leaderboard(ChristmasLeaderboardCalculator())
    val sidebarCreator = ChristmasGameSidebarCreator(leaderboard, textFormatter)

    init {
        instance = christmasMapWorld.createInstanceContainer()
        instance.timeRate = 0

        registerWorkstations()
    }

    override fun forceStartGame(): Boolean {
        players.first().sendPackets()
        return if(startingPhase.active) {
            startingPhase.switchNextPhase()
            true
        } else false
    }

    private fun registerWorkstations() {
        registerWorkstations(
            "anvil" to AnvilWorkstationHandler(workstationEventNode),
            "collect_battery" to WorkstationHandlerImpl(),
            "collect_metal" to ItemCollectionWorkstationHandler(Items.METAL.createItemStack()),
            "collect_plastic" to ItemCollectionWorkstationHandler(Items.PLASTIC.createItemStack()),
            "collect_stuffing" to WorkstationHandlerImpl(),
            "collect_wood" to ItemCollectionWorkstationHandler(Items.WOOD.createItemStack()),
            "crafting" to CraftingWorkstationHandler(workstationEventNode),
            "paint" to PaintingWorkstationHandler(workstationEventNode),
            "plastic_molding" to PlasticMolderWorkstationHandler(workstationEventNode),
            "santa_sleigh" to SantaSleighWorkstationHandler(this),
            "stuffing" to StuffingWorkstationHandler(),
            "woodcutting" to WoodcuttingWorkstationHandler(workstationEventNode),
            "wrapping" to WrappingWorkstationHandler(workstationEventNode)
        )
    }

    private fun registerWorkstations(vararg pairs: Pair<String, WorkstationHandler>) {
        pairs.forEach { registerWorkstation(fromJson(it.first), it.second) }
    }

    private fun registerWorkstation(interactionBoxes: List<WorkstationInteractionBox>, workstationHandler: WorkstationHandler) {
        interactionBoxes.forEach { workstationRegistry.register(Workstation(it, workstationHandler)) }
    }

    private fun fromJson(name: String): List<WorkstationInteractionBox> = Json.decodeFromString(File("./src/main/resources/workstations/$name.json").readText())

    companion object {
        fun create(): ChristmasGame = ChristmasGame(GameRegistryService.findAvailableId())
    }
}