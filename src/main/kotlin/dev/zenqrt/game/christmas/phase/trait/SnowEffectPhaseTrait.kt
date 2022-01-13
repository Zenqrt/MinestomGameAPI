package dev.zenqrt.game.christmas.phase.trait

import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.trait.PhaseTrait
import dev.zenqrt.game.christmas.game.ChristmasGame
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.network.packet.server.play.ChangeGameStatePacket
import world.cepi.kstom.event.listen

class SnowEffectPhaseTrait(private val eventNode: EventNode<Event>, private val game: ChristmasGame) : PhaseTrait {
    override fun handleTrait() {
        eventNode.listen<GamePlayerPostJoinEvent> {
            filters += GameFilter(game)
            handler { sendSnowPackets(player) }
        }
    }

    private fun sendSnowPackets(player: Player) {
        player.sendPackets(
            ChangeGameStatePacket(ChangeGameStatePacket.Reason.BEGIN_RAINING, 0F),
            ChangeGameStatePacket(ChangeGameStatePacket.Reason.RAIN_LEVEL_CHANGE, 1F)
        )
    }
}