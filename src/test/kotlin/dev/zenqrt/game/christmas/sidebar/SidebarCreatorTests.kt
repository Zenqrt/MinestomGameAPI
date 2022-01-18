package dev.zenqrt.game.christmas.sidebar

import dev.zenqrt.game.TestUtils
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.server.MinestomServer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.MinecraftServer
import net.minestom.server.scoreboard.Sidebar
import kotlin.math.exp

class SidebarCreatorTests : ShouldSpec({
    beforeSpec {
        MinecraftServer.init()
        MinestomServer.registerWorlds()
    }

    context("SidebarCreator") {
        val textFormatter = ChristmasTextFormatter()

        context("4 player Leaderboard") {
            val leaderboard = TestUtils.createLeaderboard(7, 5, 4, 3)
            val (firstPlace, secondPlace, thirdPlace, fourthPlace) = leaderboard.leaderboard
            val sidebarCreator = ChristmasGameSidebarCreator(leaderboard, textFormatter)

            sidebarCreator.updateTopLeaderboard()

            should("display fourth place viewer sidebar") {
                val expected = SidebarBuilder(sidebarCreator.title)
                    .createNewLine(SidebarBuilder.Line("1_place", sidebarCreator.getToysBuiltLine(firstPlace)))
                    .createNewLine(SidebarBuilder.Line("2_place", sidebarCreator.getToysBuiltLine(secondPlace)))
                    .createNewLine(SidebarBuilder.Line("3_place", sidebarCreator.getToysBuiltLine(thirdPlace)))
                    .createNewLine(SidebarBuilder.Line("separator", Component.text("...", NamedTextColor.GRAY)))
                    .createNewLine(SidebarBuilder.Line("self_place", sidebarCreator.getToysBuiltLine(fourthPlace)))
                    .createNewLine(SidebarBuilder.Line("empty", Component.empty()))
                    .createNewLine(SidebarBuilder.Line("footer", Component.text("wrong holiday...", NamedTextColor.YELLOW)))
                    .build()
                val actual = sidebarCreator.buildGameSidebar(fourthPlace)

                assertSidebar(expected, actual)
            }

            should("display second place viewer sidebar") {
                val expected = SidebarBuilder(sidebarCreator.title)
                    .createNewLine(SidebarBuilder.Line("1_place", sidebarCreator.getToysBuiltLine(firstPlace)))
                    .createNewLine(SidebarBuilder.Line("2_place", sidebarCreator.getToysBuiltLine(secondPlace)))
                    .createNewLine(SidebarBuilder.Line("3_place", sidebarCreator.getToysBuiltLine(thirdPlace)))
                    .createNewLine(SidebarBuilder.Line("empty", Component.empty()))
                    .createNewLine(SidebarBuilder.Line("footer", Component.text("wrong holiday...", NamedTextColor.YELLOW)))
                    .build()
                val actual = sidebarCreator.buildGameSidebar(secondPlace)

                assertSidebar(expected, actual)
            }
        }

        context("1 player Leaderboard") {
            val leaderboard = TestUtils.createLeaderboard(0)
            val (firstPlace) = leaderboard.leaderboard
            val sidebarCreator = ChristmasGameSidebarCreator(leaderboard, textFormatter)

            sidebarCreator.updateTopLeaderboard()

            should("display first place viewer sidebar") {
                val expected = SidebarBuilder(sidebarCreator.title)
                    .createNewLine(SidebarBuilder.Line("1_place", sidebarCreator.getToysBuiltLine(firstPlace)))
                    .createNewLine(SidebarBuilder.Line("empty", Component.empty()))
                    .createNewLine(SidebarBuilder.Line("footer", Component.text("wrong holiday...", NamedTextColor.YELLOW)))
                    .build()
                val actual = sidebarCreator.buildGameSidebar(firstPlace)

                assertSidebar(expected, actual)
            }
        }
    }
})

private fun assertSidebar(expected: Sidebar, actual: Sidebar) {
    expected.lines.size shouldBeExactly actual.lines.size

    expected.lines.forEach {
        val line = actual.getLine(it.id)!!

        it.content shouldBe line.content
        it.line shouldBeExactly  line.line
    }
}