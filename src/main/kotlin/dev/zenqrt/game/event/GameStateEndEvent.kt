package dev.zenqrt.game.event

import dev.zenqrt.game.Game
import dev.zenqrt.game.state.GameState

class GameStateEndEvent(game: Game, state: GameState) : GameStateEvent(game, state)