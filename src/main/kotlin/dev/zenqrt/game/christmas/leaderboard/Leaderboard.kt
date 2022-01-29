package dev.zenqrt.game.christmas.leaderboard

import dev.zenqrt.game.api.GamePlayer
import net.minestom.server.entity.Player

typealias LeaderboardPlayers<T> = MutableList<LeaderboardPlayer<T>>
typealias LeaderboardPlayer<T> = Pair<Player, T>

class Leaderboard<T : GamePlayer>(private val leaderboardCalculator: LeaderboardCalculator<T>) : Iterable<LeaderboardPlayer<T>> {
    var leaderboard: LeaderboardPlayers<T> = mutableListOf()

    fun updateLeaderboard(players: Map<Player, T>) {
        leaderboard = leaderboardCalculator.calculateLeaderboard(players)
    }

    fun updatePlayer(player: LeaderboardPlayer<T>) {
        var replaced = false

        leaderboard.forEachIndexed { index, pair ->
            if(player.first == pair.first && !replaced) {
                leaderboard[index] = player
                replaced = true
            }
        }
    }

    fun getTopLeaderboard(maxPlace: Int): LeaderboardPlayers<T> = when {
        leaderboard.size == 0 -> mutableListOf()
        leaderboard.size >= maxPlace -> leaderboard.subList(0, maxPlace)
        else -> leaderboard.subList(0, leaderboard.size)
    }

    override fun iterator(): Iterator<LeaderboardPlayer<T>> = leaderboard.listIterator()
}