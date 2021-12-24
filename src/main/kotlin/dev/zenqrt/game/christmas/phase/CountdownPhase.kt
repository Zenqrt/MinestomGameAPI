package dev.zenqrt.game.christmas.phase

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.GamePhase
import dev.zenqrt.game.api.phase.trait.MaxPlayerLimitCancelEventTrait
import dev.zenqrt.game.christmas.chat.ChristmasTextFormatter
import dev.zenqrt.game.christmas.game.GameOptions
import dev.zenqrt.game.christmas.phase.trait.PlayerCountBossBarPhaseTrait
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.MinecraftServer
import net.minestom.server.event.EventListener
import java.time.Duration

class CountdownPhase(private val game: Game, private val gameOptions: GameOptions, private val messageFormatter: ChristmasTextFormatter) : GamePhase("countdown") {
    override val nextPhase = { if(game.gamePlayers.size < gameOptions.minPlayers) WaitingPhase(game, gameOptions, messageFormatter) else GameCountdownPhase(game) }
    private val countdownTask = MinecraftServer.getSchedulerManager().buildTask {
        if(timer <= 0) {
            changePhase()
            return@buildTask
        }

        if(timer % 10 == 0 || timer <= 5) {
            game.broadcastActionBar(MiniMessage.get().parse(messageFormatter.formatMessage("The game will start in ${messageFormatter.formatNumber(timer)} seconds!")))
        }
    }

    private var timer = gameOptions.countdownTime

    override fun start() {
        listenPhaseChangeCondition(EventListener.builder(GamePlayerPostJoinEvent::class.java)
            .filter(GameFilter(game))) { it.game.gamePlayers.size < gameOptions.minPlayers }

        addTrait(MaxPlayerLimitCancelEventTrait(eventNode, EventListener.builder(GamePlayerJoinEvent::class.java), game, gameOptions.maxPlayers))
        addTrait(PlayerCountBossBarPhaseTrait(game, eventNode, messageFormatter, gameOptions.maxPlayers))

        countdownTask.repeat(Duration.ofSeconds(1)).schedule()
    }

    override fun end() {
    }
}