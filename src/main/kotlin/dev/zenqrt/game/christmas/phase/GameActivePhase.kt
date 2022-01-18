package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.event.GamePlayerPostLeaveEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.event.filter.GamePlayerFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.api.phase.trait.CancelEventTrait
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.ChristmasGamePlayer
import dev.zenqrt.game.christmas.game.GameOptions
import dev.zenqrt.game.christmas.leaderboard.LeaderboardPlayer
import dev.zenqrt.game.christmas.phase.trait.GameTimerBossBarPhaseTrait
import dev.zenqrt.game.christmas.phase.trait.WorkstationPhaseTrait
import dev.zenqrt.game.christmas.sidebar.ChristmasGameSidebarCreator
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import net.minestom.server.event.EventListener
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class GameActivePhase(private val game: ChristmasGame, private val gameOptions: GameOptions, private val textFormatter: ChristmasTextFormatter) : GamePhase("active") {
    override val nextPhase = { EndingPhase(game, textFormatter) }
    private val sidebarCreator = ChristmasGameSidebarCreator(game.leaderboard, textFormatter)

    override fun start() {
        if(attemptForceEnd()) return

        registerListeners()
        registerTraits()
        setupInstance()
        setupLeaderboard()
        setupPlayers()
    }

    private fun registerListeners() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostLeaveEvent::class.java)
            .filter(GameFilter(game))) { shouldForceEnd() }
    }

    private fun registerTraits() {
        addTrait(GameTimerBossBarPhaseTrait(game, this, gameOptions.gameTime))
        addTrait(WorkstationPhaseTrait(eventNode, game))
        addTrait(CancelEventTrait(eventNode, EventListener.builder(InventoryPreClickEvent::class.java)
            .filter(GamePlayerFilter(game))
            .filter { it.inventory?.inventoryType == null && it.slot >= 3 }))
    }

    private fun shouldForceEnd(): Boolean = game.gamePlayers.size > 1

    private fun attemptForceEnd(): Boolean {
        if(shouldForceEnd()) {
            switchNextPhase()
            return true
        }
        return false
    }

    private fun setupInstance() {
        val instance = game.instance
        instance.timeRate = 4
        instance.time = 0
    }

    private fun setupLeaderboard() {
        game.leaderboard.updateLeaderboard(game.gamePlayers)
        sidebarCreator.updateTopLeaderboard()
    }

    private fun setupPlayers() {
        game.broadcast {
            fillInventory(it)
            displaySidebar(it, game.gamePlayers[it]!!)
            teleportToSpawn(it)
            it.setHeldItemSlot(0)
        }
    }

    private fun fillInventory(player: Player) {
        val itemStack = ItemStack.builder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).displayName(
            Component.empty()).build()

        for(i in 3 until 36) {
            player.inventory.setItemStack(i, itemStack)
        }
    }

    private fun displaySidebar(player: Player, gamePlayer: ChristmasGamePlayer) {
        val sidebar = sidebarCreator.buildGameSidebar(LeaderboardPlayer(player, gamePlayer))
        sidebar.addViewer(player)
    }

    private fun teleportToSpawn(player: Player) {
        player.teleport(game.christmasMapWorld.spawnPos)
    }

    override fun end() {
        switchNextPhaseEventNode()
        endTraits()
    }
}