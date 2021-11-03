package game

import dev.zenqrt.game.Game
import dev.zenqrt.game.GamePlayer
import net.minestom.server.MinecraftServer
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GameTests {

    val game = TestGame()

    @BeforeTest
    fun startServer() {
        MinecraftServer.init()
    }
    @Test
    fun `Game should be allowed to start when minimum players are reached`() {
        for(i in 0..3) {
            game.join(GamePlayer())
        }
        assertTrue(game.canStart())
    }

    @Test
    fun `Game should end when the timer reaches 10 seconds`() {
        game.start()
        assertFalse(game.active, "Game should not be active")
    }

}