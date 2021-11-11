package dev.zenqrt.game

class GameRegistry : Registry<Game> {

    private val games = mutableMapOf<String, Game>()

    override fun register(key: String, obj: Game) {
        games[key] = obj
    }

    override fun unregister(key: String, obj: Game) {
        games.remove(key)
    }

    override fun find(key: String): Game? = games[key]

}