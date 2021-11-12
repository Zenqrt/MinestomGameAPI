package game

import dev.zenqrt.game.Game
import dev.zenqrt.game.GameOptions
import dev.zenqrt.game.GamePlayer
import dev.zenqrt.game.GameRegistry
import dev.zenqrt.game.handler.GamePlayerHandlerImpl
import dev.zenqrt.game.handler.StateTickHandler
import dev.zenqrt.game.state.EndingState
import dev.zenqrt.game.state.GameActiveState
import dev.zenqrt.game.state.TimerState
import game.phase.TestActivePhase
import game.phase.TestEndingPhase
import game.phase.TestStartPhase
import game.player.AccessibleFakePlayer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import net.minestom.server.MinecraftServer
import java.util.*
import io.kotest.matchers.types.shouldBeInstanceOf

class GameTests : ShouldSpec({

    beforeSpec { MinecraftServer.init() }

    val registry = GameRegistry()
    val gameOptions = GameOptions(8, 4)
    val gamePlayerHandler = GamePlayerHandlerImpl(gameOptions)
    val tickHandler = StateTickHandler(gameOptions, TestStartPhase(), TestActivePhase(), TestEndingPhase(), gamePlayerHandler)
    val game = Game(gamePlayerHandler, tickHandler)

    should("register game into registry") {
        registry.register("test", game)
        registry.find("test") shouldNotBe null
    }

    should("start game when 4 players join the game") {
        for (i in 1..4) {
            game.join(GamePlayer(AccessibleFakePlayer(UUID.randomUUID(), "fake_player")))
        }
        tickHandler.shouldStart() shouldBe true
    }

    should("finish start phase after 10 ticks") {
        for(i in 1..10) {
            game.tick()
        }
        tickHandler.currentState.shouldBeInstanceOf<GameActiveState>()
    }

    should("finish game active phase after 1 tick") {
        game.tick()
        tickHandler.currentState.shouldBeInstanceOf<EndingState>()
    }

    should("have a null state after 5 ticks") {
        for(i in 1..5) {
            game.tick()
        }
        tickHandler.currentState shouldBe null
    }
})