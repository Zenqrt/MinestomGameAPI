package dev.zenqrt.game.christmas.leaderboard

import dev.zenqrt.game.TestUtils
import dev.zenqrt.game.api.player.AccessibleFakePlayer
import dev.zenqrt.game.christmas.game.ChristmasGamePlayer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import java.util.*

class LeaderboardTests : ShouldSpec({
    beforeSpec { MinecraftServer.init() }

    context("Leaderboard") {
        should("calculate leaderboard") {
            val leaderboard = TestUtils.createLeaderboard(5, 3, 1)
            val (firstPlace, secondPlace, thirdPlace) = leaderboard.leaderboard

            leaderboard.leaderboard[0] shouldBe firstPlace
            leaderboard.leaderboard[1] shouldBe secondPlace
            leaderboard.leaderboard[2] shouldBe thirdPlace
        }

        should("show top 3 leaderboard") {
            val leaderboard = TestUtils.createLeaderboard(7, 5, 3, 1, 0)
            val (firstPlace, secondPlace, thirdPlace, fourthPlace, fifthPlace) = leaderboard.leaderboard
            val topLeaderboard = leaderboard.getTopLeaderboard(3)

            topLeaderboard[0] shouldBe firstPlace
            topLeaderboard[1] shouldBe secondPlace
            topLeaderboard[2] shouldBe thirdPlace
            topLeaderboard shouldNotContain fourthPlace
            topLeaderboard shouldNotContain fifthPlace
        }

        should("show top 3 leaderboard with only 1 player") {
            val leaderboard = TestUtils.createLeaderboard(0)
            val (firstPlace) = leaderboard.leaderboard
            val topLeaderboard = leaderboard.getTopLeaderboard(3)

            topLeaderboard[0] shouldBe firstPlace
        }
    }

})