package dev.zenqrt.game.api.event.trait

import dev.zenqrt.game.api.phase.GamePhase

interface GameStateEvent : GameEvent {
    val gameState: GamePhase
}