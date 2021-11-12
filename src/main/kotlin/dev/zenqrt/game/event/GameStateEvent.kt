package dev.zenqrt.game.event

import dev.zenqrt.game.Game
import dev.zenqrt.game.state.GameState

open class GameStateEvent(game: Game, val state: GameState) : GameEvent(game)