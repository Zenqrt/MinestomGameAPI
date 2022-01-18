package dev.zenqrt.game.christmas.sidebar

import net.kyori.adventure.text.Component
import net.minestom.server.scoreboard.Sidebar

class SidebarBuilder(val title: Component) {
    constructor(title: String) : this(Component.text(title))

    private val lines = mutableListOf<Line>()

    fun addLines(lineNumber: Int, lines: List<Line>): SidebarBuilder = this.also {
        if(lines.isNotEmpty()) {
            lines.forEach { this.lines.add(lineNumber, it) }
        }
    }
    fun addNewLines(lines: List<Line>): SidebarBuilder = this.also { if(lines.isNotEmpty()) addLines(0, lines) }

    fun createLine(lineNumber: Int, line: Line): SidebarBuilder = this.also { lines.add(lineNumber, line) }
    fun createNewLine(line: Line): SidebarBuilder = this.also { createLine(0, line) }

    fun build(): Sidebar = Sidebar(title).also {
        lines.forEachIndexed { index, line ->
            it.createLine(Sidebar.ScoreboardLine(line.id, line.content, index))
        }
    }

    data class Line(val id: String, val content: Component)
}