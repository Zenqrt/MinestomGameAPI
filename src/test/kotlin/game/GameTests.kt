package game

import dev.zenqrt.game.Game
import dev.zenqrt.game.GameOptions
import dev.zenqrt.game.GameRegistry
import dev.zenqrt.game.handler.GamePlayerHandlerImpl
import dev.zenqrt.game.handler.TickHandlerImpl
import game.phase.TestActivePhase
import game.phase.TestEndingPhase
import game.phase.TestStartPhase
import kotlin.test.Test
import kotlin.test.assertNotNull

class GameTests {

    private val registry = GameRegistry()

    @Test
    fun `Should register game into registry`() {
        val gameOptions = GameOptions(8, 4)
        val game = Game(GamePlayerHandlerImpl(gameOptions), TickHandlerImpl(gameOptions, TestStartPhase(), TestActivePhase(), TestEndingPhase()))
        registry.register("test", game)
        assertNotNull(registry.find("test"))
    }

}