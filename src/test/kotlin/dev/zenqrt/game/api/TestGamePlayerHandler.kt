package dev.zenqrt.game.api

import net.minestom.server.entity.Player
import java.util.*

class TestGamePlayerHandler : AbstractGamePlayerHandler<TestGamePlayer>() {
    override fun createPlayer(player: Player): TestGamePlayer = TestGamePlayer(player.uuid)
}

data class TestGamePlayer(override val uuid: UUID) : GamePlayer()