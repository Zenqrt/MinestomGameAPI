package dev.zenqrt.game.christmas.leaderboard

import dev.zenqrt.game.api.GamePlayer
import net.minestom.server.entity.Player

interface LeaderboardCalculator<T : GamePlayer> {
    fun calculateLeaderboard(players: Map<Player, T>): List<Pair<Player, T>>
}