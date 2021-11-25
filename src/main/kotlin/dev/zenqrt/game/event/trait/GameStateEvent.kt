package dev.zenqrt.game.event.trait

import dev.zenqrt.game.phase.GamePhase

interface GameStateEvent : GameEvent {
    val gameState: GamePhase
}