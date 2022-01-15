package dev.zenqrt.game.christmas.phase.trait

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.chat.TextFormatter
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.GamePlayerPostLeaveEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.event.filter.GamePlayerFilter
import dev.zenqrt.game.api.event.trait.GameEvent
import dev.zenqrt.game.api.event.trait.GamePlayerEvent
import dev.zenqrt.game.api.phase.trait.PhaseTrait
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import net.minestom.server.network.packet.server.play.ChangeGameStatePacket
import world.cepi.kstom.adventure.asMini
import world.cepi.kstom.event.listen

class PlayerActivityBossBarPhaseTrait(private val game: Game<out GamePlayer>,
                                      private val eventNode: EventNode<Event>,
                                      private val messageFormatter: TextFormatter<String>,
                                      private val maxPlayers: Int) : PhaseTrait {
    val playerCountBossBar = BossBar.bossBar(playerCountText, playerCountProgress, BossBar.Color.BLUE, BossBar.Overlay.PROGRESS)
    val playerCountText: Component
        get() = Component.text("Players: ${game.gamePlayers.size}/$maxPlayers")
    private val playerCountProgress: Float
        get() = game.gamePlayers.size.toFloat() / maxPlayers.toFloat()

    override fun handleTrait() {
        game.showBossBar(playerCountBossBar)
        addBroadcastActivityListener<GamePlayerPostJoinEvent>("joined") { it.player.showBossBar(playerCountBossBar) }
        addBroadcastActivityListener<GamePlayerPostLeaveEvent>("left") { it.player.hideBossBar(playerCountBossBar) }
    }

    private inline fun <reified T : GamePlayerEvent> addBroadcastActivityListener(activityMessage: String, crossinline handler: (T) -> Unit) {
        addUpdatePlayerListener<T> {
            handler(it)
            broadcastActivityMessage(it.player, activityMessage)
        }
    }

    private fun broadcastActivityMessage(player: Player, activityMessage: String) {
        game.sendMessage(messageFormatter.formatMessage("${messageFormatter.formatUsername(player.username)} $activityMessage the game!").asMini())
    }

    private inline fun <reified T : GamePlayerEvent> addUpdatePlayerListener(crossinline handler: (T) -> Unit) {
        eventNode.listen<T> {
            filters += GameFilter(game)

            handler {
                handler(this)
                updatePlayerCountBossBar()
            }
        }
    }

    private fun updatePlayerCountBossBar() {
        playerCountBossBar.name(playerCountText)
        playerCountBossBar.progress(playerCountProgress)
    }

    override fun endTrait() {
        game.hideBossBar(playerCountBossBar)
    }
}