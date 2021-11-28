package game.player

import dev.zenqrt.game.GamePlayer
import java.util.*

class FakeGamePlayer(name: String) : GamePlayer(AccessibleFakePlayer(UUID.randomUUID(), name))