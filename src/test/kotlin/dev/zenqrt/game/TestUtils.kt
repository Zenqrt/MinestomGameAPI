package dev.zenqrt.game

import dev.zenqrt.game.api.TestGame
import dev.zenqrt.game.api.player.AccessibleFakePlayer
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.ChristmasGamePlayer
import dev.zenqrt.game.christmas.leaderboard.ChristmasLeaderboardCalculator
import dev.zenqrt.game.christmas.leaderboard.Leaderboard
import dev.zenqrt.game.christmas.leaderboard.LeaderboardPlayer
import net.minestom.server.entity.Player
import java.util.*

object TestUtils {
    fun registerFakePlayer(game: TestGame): AccessibleFakePlayer = AccessibleFakePlayer(UUID.randomUUID(), "test_player").also {
        game.insertPlayer(game.createPlayer(it), it, game)
    }

    fun createChristmasGamePlayer(game: ChristmasGame, username: String): Pair<AccessibleFakePlayer, ChristmasGamePlayer> = AccessibleFakePlayer.create(username).let { Pair(it, game.createPlayer(it)) }

    fun createLeaderboard(vararg toysBuilt: Int): Leaderboard<ChristmasGamePlayer> = Leaderboard(ChristmasLeaderboardCalculator()).also {
        val players = mutableMapOf<Player, ChristmasGamePlayer>()

        toysBuilt.forEachIndexed { index, i ->
            val player = AccessibleFakePlayer.create("fake_player$index")
            players[player] = ChristmasGamePlayer(player.uuid, i)
        }

        it.updateLeaderboard(players)
    }

    private fun mapPlayers(vararg players: LeaderboardPlayer<ChristmasGamePlayer>): Map<Player, ChristmasGamePlayer> = mutableMapOf<Player, ChristmasGamePlayer>().also {
        players.forEach { player ->
            it[player.first] = player.second
        }
    }

    private fun createLeaderboardPlayer(username: String, toysBuilt: Int): LeaderboardPlayer<ChristmasGamePlayer> = AccessibleFakePlayer(
        UUID.randomUUID(), username).let { LeaderboardPlayer(it, ChristmasGamePlayer(it.uuid, toysBuilt)) }
}