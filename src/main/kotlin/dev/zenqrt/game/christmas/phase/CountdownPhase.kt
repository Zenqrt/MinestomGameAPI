package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.christmas.chat.HexColor
import dev.zenqrt.game.christmas.game.GameOptions
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.MinecraftServer
import net.minestom.server.event.EventListener
import java.time.Duration

class CountdownPhase(private val game: Game, private val gameOptions: GameOptions) : GamePhase("countdown") {
    override val nextPhase = { if(game.gamePlayers.size < gameOptions.minPlayers) WaitingPhase(game, gameOptions) else GameCountdownPhase(game) }
    private val countdownTask = MinecraftServer.getSchedulerManager().buildTask {
        if(timer <= 0) {
            changePhase()
            return@buildTask
        }

        if(timer % 10 == 0 || timer <= 5) {
            game.gamePlayers.keys.forEach { it.sendActionBar(MiniMessage.get().parse("<${HexColor.TEXT_GRADIENT}>The game will start in <${HexColor.VALUE}>$timer</color> seconds!")) }
        }
    }

    private var timer = gameOptions.countdownTime

    override fun start() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostJoinEvent::class.java)
            .filter(GameFilter(game))) { it.game.gamePlayers.size < gameOptions.minPlayers }

        countdownTask.repeat(Duration.ofSeconds(1)).schedule()
    }

    override fun end() {
    }
}