package game

import dev.zenqrt.game.GameManager
import net.minestom.server.MinecraftServer
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFalse

/*
GameManager:
registerGame()
unregisterGame()
 */
class GameManagerTests {

    @BeforeTest
    fun startServer() {
        MinecraftServer.init()
    }

    @Test
    fun `Should register game to the game registry`() {
        val gameManager = GameManager()
        val game = TestGame()
        gameManager.register(game)

        assertContains(gameManager.games, game, "Should contain game in games list")
    }

    @Test
    fun `Should unregister game from the game registry`() {
        val gameManager = GameManager()
        val game = TestGame()
        gameManager.register(game)
        assertContains(gameManager.games, game, "Should contain game in games list")

        gameManager.unregister(game)
        assertFalse(gameManager.games.contains(game), "Should not contain game in games list")
    }

}