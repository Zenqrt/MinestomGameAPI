package dev.zenqrt.game.christmas.phase.trait

import dev.zenqrt.game.api.phase.trait.PhaseTrait
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.ChristmasGamePlayer
import dev.zenqrt.game.christmas.leaderboard.ChristmasLeaderboardCalculator
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import net.minestom.server.entity.Player
import world.cepi.kstom.adventure.asMini

class DisplayLeaderboardPhaseTrait(private val game: ChristmasGame, private val textFormatter: ChristmasTextFormatter, private val leaderboardCalculator: ChristmasLeaderboardCalculator) : PhaseTrait {
    lateinit var leaderboard: List<Pair<Player, ChristmasGamePlayer>>

    override fun handleTrait() {
        calculateLeaderboard()
        val firstPlace = getLeaderboardPlayer(leaderboard, 0)
        val secondPlace = getLeaderboardPlayer(leaderboard, 1)
        val thirdPlace = getLeaderboardPlayer(leaderboard, 2)

        game.sendMessage(buildLeaderboardMessage(firstPlace, secondPlace, thirdPlace))
        sendTitles(firstPlace, secondPlace, thirdPlace, leaderboard)
    }

    fun calculateLeaderboard() {
        leaderboard = leaderboardCalculator.calculateLeaderboard(game.gamePlayers)
    }

    private fun sendTitles(firstPlace: Player?, secondPlace: Player?, thirdPlace: Player?, leaderboard: List<Pair<Player, ChristmasGamePlayer>>) {
        firstPlace?.showTitle(FIRST_PLACE_TITLE)
        secondPlace?.showTitle(SECOND_PLACE_TITLE)
        thirdPlace?.showTitle(THIRD_PLACE_TITLE)

        leaderboard.subList(3, leaderboard.size - 1).forEachIndexed { index, pair ->
            pair.first.showTitle(Title.title("$OTHER_PLACE_COLOR<bold>${index}th Place".asMini(), "<gray>Yikes...".asMini()))
        }
    }

    private fun buildLeaderboardMessage(firstPlace: Player?, secondPlace: Player?, thirdPlace: Player?): Component = buildBorderText()
        .append(Component.newline())
        .append("""
            $FIRST_PLACE_COLOR<bold>1st Place</bold></gradient> <gray>-</gray> ${buildFormattedUsername(firstPlace)}
            $SECOND_PLACE_COLOR<bold>2nd Place</bold></gradient> <gray>-</gray> ${buildFormattedUsername(secondPlace)}
            $THIRD_PLACE_COLOR<bold>3rd Place</bold></gradient> <gray>-</gray> ${buildFormattedUsername(thirdPlace)}
        """.trimIndent().asMini())
        .append(Component.newline())
        .append(buildBorderText())

    private fun buildBorderText(): Component = "<gradient:#a3e3e6:#a3bbe6>------------------------------------------</gradient>".asMini()
    private fun buildFormattedUsername(player: Player?): String =
        if(player == null) "<red>None</red>" else textFormatter.formatUsername(player.username)

    private fun getLeaderboardPlayer(leaderboard: List<Pair<Player, ChristmasGamePlayer>>, position: Int): Player? = if(position >= leaderboard.size) null else leaderboard[position].first

    companion object {
        private const val FIRST_PLACE_COLOR = "<gradient:#52ff4f:#89ff87>"
        private const val SECOND_PLACE_COLOR = "<gradient:#36ff65:#75ff98>"
        private const val THIRD_PLACE_COLOR = "<gradient:#36ff89:#75ffaf>"
        private const val OTHER_PLACE_COLOR = "<gradient:#33ffd6:#59a1ff>"

        private val FIRST_PLACE_TITLE = Title.title("$FIRST_PLACE_COLOR<bold>1st Place".asMini(), "<gray>You win!".asMini())
        private val SECOND_PLACE_TITLE = Title.title("$SECOND_PLACE_COLOR<bold>2nd Place".asMini(), "<gray>So close!".asMini())
        private val THIRD_PLACE_TITLE = Title.title("$THIRD_PLACE_COLOR<bold>3rd Place".asMini(), "<grayBetter luck next time!".asMini())
    }
}