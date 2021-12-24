package dev.zenqrt.game.api.event

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.event.trait.GameStateEvent
import dev.zenqrt.game.api.phase.GamePhase

class GamePhaseEndEvent(override val game: Game, override val gameState: GamePhase) : GameStateEvent