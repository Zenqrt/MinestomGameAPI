package dev.zenqrt.game

class GameManager {
    val games = mutableListOf<Game>()

    fun register(game: Game) {
        games.add(game)
    }

    fun unregister(game: Game) {
        games.remove(game)
    }
}
