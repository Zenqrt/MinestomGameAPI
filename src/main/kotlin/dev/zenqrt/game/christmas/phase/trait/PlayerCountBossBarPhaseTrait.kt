package dev.zenqrt.game.christmas.phase.trait

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.chat.TextFormatter
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.GamePlayerPostLeaveEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.event.trait.GameEvent
import dev.zenqrt.game.api.phase.trait.PhaseTrait
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import net.minestom.server.network.packet.server.play.ChangeGameStatePacket
import world.cepi.kstom.event.listen

class PlayerCountBossBarPhaseTrait(private val game: Game<out GamePlayer>,
                                   private val eventNode: EventNode<Event>,
                                   private val messageFormatter: TextFormatter<String>,
                                   private val maxPlayers: Int) : PhaseTrait {
    private val playerCountBossBar = BossBar.bossBar(getPlayerCountText(game.gamePlayers.size), getPlayerCountProgress(game.gamePlayers.size), BossBar.Color.BLUE, BossBar.Overlay.PROGRESS)
    private val miniMessage = MiniMessage.get()

    override fun handleTrait() {
        addUpdatePlayerListener<GamePlayerPostJoinEvent> {
            sendSnowPackets(it.player)
            broadcastActivityMessage(it.player, "joined")
        }

        addUpdatePlayerListener<GamePlayerPostLeaveEvent> { broadcastActivityMessage(it.player, "left") }
    }

    private fun sendSnowPackets(player: Player) {
        player.sendPackets(
            ChangeGameStatePacket(ChangeGameStatePacket.Reason.BEGIN_RAINING, 0F),
            ChangeGameStatePacket(ChangeGameStatePacket.Reason.RAIN_LEVEL_CHANGE, 1F)
        )
    }

    private fun broadcastActivityMessage(player: Player, activityMessage: String) {
        game.sendMessage(miniMessage.parse(messageFormatter.formatMessage("${messageFormatter.formatUsername(player.username)} $activityMessage the game!")))
    }

    private inline fun <reified T : GameEvent> addUpdatePlayerListener(crossinline handler: (T) -> Unit) {
        eventNode.listen<T> {
            filters += GameFilter(game)

            handler {
                handler(this)
                updatePlayerCountBossBar()
            }
        }
    }

    private fun updatePlayerCountBossBar() {
        val playerCount = game.gamePlayers.size
        playerCountBossBar.name(getPlayerCountText(playerCount))
        playerCountBossBar.progress(getPlayerCountProgress(playerCount))
    }

    private fun getPlayerCountText(playerCount: Int): Component = Component.text("Players: $playerCount/$maxPlayers")
    private fun getPlayerCountProgress(playerCount: Int): Float = playerCount.toFloat() / maxPlayers.toFloat()
}