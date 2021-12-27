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
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode

class PlayerCountBossBarPhaseTrait(private val game: Game<out GamePlayer>, private val eventNode: EventNode<Event>, private val messageFormatter: TextFormatter<String>, private val maxPlayers: Int,) : PhaseTrait {
    private val playerCountBossBar = BossBar.bossBar(getPlayerCountText(game.gamePlayers.size), getPlayerCountProgress(game.gamePlayers.size), BossBar.Color.BLUE, BossBar.Overlay.PROGRESS)

    override fun handleTrait() {
        addUpdatePlayerListener<GamePlayerPostJoinEvent> {
            it.game.broadcastMessage(MiniMessage.get().parse(messageFormatter.formatMessage("${messageFormatter.formatUsername(it.player.username)} joined the game!")))
        }
        addUpdatePlayerListener<GamePlayerPostLeaveEvent> {
            it.game.broadcastMessage(MiniMessage.get().parse(messageFormatter.formatMessage("${messageFormatter.formatMessage(it.player.username)} left the game!")))
        }
    }

    private inline fun <reified T : GameEvent> addUpdatePlayerListener(crossinline handler: (T) -> Unit) {
        eventNode.addListener(
            EventListener.builder(T::class.java)
            .filter(GameFilter(game))
            .handler {
                handler.invoke(it)
                updatePlayerCountBossbar()
            }.build())
    }

    private fun updatePlayerCountBossbar() {
        val playerCount = game.gamePlayers.size
        playerCountBossBar.name(getPlayerCountText(playerCount))
        playerCountBossBar.progress(getPlayerCountProgress(playerCount))
    }

    private fun getPlayerCountText(playerCount: Int): Component = Component.text("Players: $playerCount/$maxPlayers")
    private fun getPlayerCountProgress(playerCount: Int): Float = playerCount.toFloat() / maxPlayers.toFloat()
}