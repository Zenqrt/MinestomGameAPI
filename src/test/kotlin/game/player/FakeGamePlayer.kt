package game.player

import dev.zenqrt.game.GamePlayer
import net.minestom.server.entity.fakeplayer.FakePlayer
import java.util.*
import java.util.function.Consumer

class FakeGamePlayer(name: String, spawnCallback: Consumer<FakePlayer>? = null) : GamePlayer(AccessibleFakePlayer(UUID.randomUUID(), name, spawnCallback))