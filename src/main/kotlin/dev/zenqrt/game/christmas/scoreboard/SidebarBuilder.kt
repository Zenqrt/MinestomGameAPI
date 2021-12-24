package dev.zenqrt.game.christmas.scoreboard

import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.scoreboard.Sidebar

object SidebarBuilder {

    /**
     * CHRISTMAS EVENT
     * ---------------
     * Waiting...
     *
     * Players: 3/8
     */

    fun buildGameSidebar(): Sidebar = Sidebar(MiniMessage.get().parse("<gradient:#80b0ff:#ffffff><bold>CHRISTMAS EVENT"))
//    fun buildWaitingSidebar(): Sidebar {
//        val sidebar = buildGameSidebar()
//        sidebar.createLine(Sidebar.ScoreboardLine(""))
//    }
}