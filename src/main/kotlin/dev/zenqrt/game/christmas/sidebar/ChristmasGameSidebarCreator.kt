package dev.zenqrt.game.christmas.sidebar

import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGamePlayer
import dev.zenqrt.game.christmas.leaderboard.Leaderboard
import dev.zenqrt.game.christmas.leaderboard.LeaderboardPlayer
import dev.zenqrt.game.christmas.leaderboard.LeaderboardPlayers
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.scoreboard.Sidebar
import world.cepi.kstom.adventure.asMini

class ChristmasGameSidebarCreator(private val leaderboard: Leaderboard<ChristmasGamePlayer>, private val textFormatter: ChristmasTextFormatter) {
    val title = "<gradient:#80b0ff:#ffffff><bold>CHRISTMAS EVENT".asMini()
    private var leaderboardCache: List<SidebarBuilder.Line> = emptyList()
    private var topLeaderboard: LeaderboardPlayers<ChristmasGamePlayer>? = null

    fun buildGameSidebar(player: LeaderboardPlayer<ChristmasGamePlayer>): Sidebar = SidebarBuilder(title).also {
        println("Sidebar: ${leaderboard.leaderboard[0].second.toysBuilt}")
        val isOnLeaderboard = topLeaderboard?.contains(player) ?: false

        it.addNewLines(leaderboardCache)

        if(!isOnLeaderboard) {
            it.createNewLine(SidebarBuilder.Line("separator", Component.text("...", NamedTextColor.GRAY)))
                .createNewLine(SidebarBuilder.Line("self_place", getToysBuiltLine(player)))
        }

        it.createNewLine(SidebarBuilder.Line("empty", Component.empty()))
            .createNewLine(SidebarBuilder.Line("footer", Component.text("wrong holiday...", NamedTextColor.YELLOW)))
    }.build()

    fun updateTopLeaderboard() {
        val cache = mutableListOf<SidebarBuilder.Line>()
        topLeaderboard = leaderboard.getTopLeaderboard(3)

        topLeaderboard!!.forEachIndexed { index, pair ->
            val line = SidebarBuilder.Line("${index + 1}_place", getToysBuiltLine(pair))
            cache.add(line)
        }

        leaderboardCache = cache
    }

    fun getToysBuiltLine(player: LeaderboardPlayer<ChristmasGamePlayer>): Component = "${textFormatter.formatUsername(player.first.username)}<gray>:</gray> <green>${player.second.toysBuilt}</green>".asMini()
}