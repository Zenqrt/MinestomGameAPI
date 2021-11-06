package dev.zenqrt.game

class GameRegistry : Registry<Game> {

    private val games = mutableListOf<Game>()

    override fun register(obj: Game) {
        games.add(obj)
    }

    override fun unregister(obj: Game) {
        games.remove(obj)
    }

}