package dev.zenqrt.game

import dev.zenqrt.game.api.TestGame
import dev.zenqrt.game.api.player.AccessibleFakePlayer
import java.util.*

object TestUtils {
    fun registerFakePlayer(game: TestGame): AccessibleFakePlayer = AccessibleFakePlayer(UUID.randomUUID(), "test_player").also {
        game.insertPlayer(game.createPlayer(it), it, game)
    }
}