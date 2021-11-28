package dev.zenqrt.game.event

import dev.zenqrt.game.Game
import dev.zenqrt.game.event.trait.GameStateEvent
import dev.zenqrt.game.phase.GamePhase

class GamePhaseEndEvent(override val game: Game, override val gameState: GamePhase) : GameStateEvent