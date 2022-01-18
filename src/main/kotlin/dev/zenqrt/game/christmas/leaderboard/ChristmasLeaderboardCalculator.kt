package dev.zenqrt.game.christmas.leaderboard

import dev.zenqrt.game.christmas.game.ChristmasGamePlayer
import net.minestom.server.entity.Player

class ChristmasLeaderboardCalculator : LeaderboardCalculator<ChristmasGamePlayer> {
    override fun calculateLeaderboard(players: Map<Player, ChristmasGamePlayer>): LeaderboardPlayers<ChristmasGamePlayer> = players.toList().sortedBy { (_, value) -> value }.toMutableList()
}