package game.phase

import dev.zenqrt.game.Game
import dev.zenqrt.game.phase.GamePhase
import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer

class EndingTestPhase(private val game: Game) : GamePhase("ending") {
    override fun start() {
        MinecraftServer.getSchedulerManager()
            .buildTask { changePhase() }
            .schedule()
    }

    override fun end() {
        game.players.forEach { it.value.player.kick("Game has ended!") }
    }
}