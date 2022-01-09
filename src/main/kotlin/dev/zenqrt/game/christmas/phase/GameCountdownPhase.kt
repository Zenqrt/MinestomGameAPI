package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.event.filter.GamePlayerFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.api.phase.trait.CancelEventTrait
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.GameOptions
import dev.zenqrt.game.christmas.timer.Countdown
import dev.zenqrt.game.christmas.timer.CountdownRunnable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.event.EventListener
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.player.PlayerMoveEvent
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.network.packet.server.play.UnlockRecipesPacket
import java.time.Duration

class GameCountdownPhase(private val game: ChristmasGame, gameOptions: GameOptions, private val textFormatter: ChristmasTextFormatter) : GamePhase("game_countdown") {
    override val nextPhase = { GameActivePhase(game, gameOptions) }
    private val spawnPosition = Pos(0.0, 0.0, 0.0)
    private val miniMessage = MiniMessage.get()
    private lateinit var countdownTask: CountdownRunnable

    override fun start() {
        setupPlayers()
        registerTraits()
        startCountdown()
    }

    private fun registerTraits() {
        addTrait(CancelEventTrait(eventNode, EventListener.builder(InventoryPreClickEvent::class.java)
            .filter(GamePlayerFilter(game))))
        addTrait(CancelEventTrait(eventNode, EventListener.builder(PlayerMoveEvent::class.java)
            .filter(GamePlayerFilter(game))))
    }

    private fun startCountdown() {
        countdownTask = Countdown.create(
            10,
            Duration.ofSeconds(1),
            afterIncrementAction = { game.sendActionBar(miniMessage.parse(textFormatter.formatMessage("You can move in ${textFormatter.formatNumber(it)} seconds!"))) },
            endingAction = { switchNextPhase() }
        )
    }

    override fun end() {
        endCountdown()
        switchAllNextPhaseEventNodes()
    }

    private fun endCountdown() {
        countdownTask.cancel()
    }

    private fun setupPlayers() {
        unlockRecipes()

        game.broadcast {
            fillInventory(it)
            teleportToSpawn(it)
        }
    }

    private fun unlockRecipes() {
        game.sendGroupedPacket(UnlockRecipesPacket(
            1,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            false,
            listOf("plastic"),
            null
        ))
    }

    private fun fillInventory(player: Player) {
        val itemStack = ItemStack.builder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).displayName(
            Component.empty()).build()

        for(i in 3 until player.inventory.size) {
            player.inventory.setItemStack(i, itemStack)
        }
    }

    private fun teleportToSpawn(player: Player) {
        player.teleport(spawnPosition)
    }
}