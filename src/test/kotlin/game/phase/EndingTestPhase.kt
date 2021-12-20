package game.phase

import dev.zenqrt.game.phase.GamePhase
import game.TestGame
import net.minestom.server.event.player.PlayerStartSprintingEvent

class EndingTestPhase(game: TestGame) : GamePhase("ending") {

    override fun start() {
        eventNode.addListener(PlayerStartSprintingEvent::class.java) {


        }
    }
    override fun end() {
        TODO("Not yet implemented")
    }
}