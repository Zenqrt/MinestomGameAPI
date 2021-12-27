package dev.zenqrt.game.christmas.game

import dev.zenqrt.game.api.AbstractGamePlayerHandler
import net.minestom.server.entity.Player

class ChristmasGamePlayerHandler : AbstractGamePlayerHandler<ChristmasGamePlayer>() {
    override fun createPlayer(player: Player): ChristmasGamePlayer = ChristmasGamePlayer(player.uuid, 0)
}