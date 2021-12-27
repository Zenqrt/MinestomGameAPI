package dev.zenqrt.game.api.player

import net.minestom.server.entity.fakeplayer.FakePlayer
import net.minestom.server.entity.fakeplayer.FakePlayerOption
import java.util.*
import java.util.function.Consumer

class AccessibleFakePlayer(uuid: UUID,
                           username: String,
                           spawnCallback: Consumer<FakePlayer>? = null,
                           option: FakePlayerOption = FakePlayerOption()
) : FakePlayer(uuid, username, option, spawnCallback)