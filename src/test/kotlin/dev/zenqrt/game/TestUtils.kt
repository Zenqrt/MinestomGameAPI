package dev.zenqrt.game

import dev.zenqrt.game.api.GamePlayerHandler
import dev.zenqrt.game.api.TestGame
import dev.zenqrt.game.api.player.AccessibleFakePlayer
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.ChristmasGamePlayer
import java.util.*

object TestUtils {
    fun registerFakePlayer(game: TestGame): AccessibleFakePlayer = AccessibleFakePlayer(UUID.randomUUID(), "test_player").also {
        game.insertPlayer(game.createPlayer(it), it, game)
    }

    fun createChristmasGamePlayer(game: ChristmasGame, username: String): Pair<AccessibleFakePlayer, ChristmasGamePlayer> = AccessibleFakePlayer.create(username).let { Pair(it, game.createPlayer(it)) }
}