package dev.zenqrt.game

abstract class Game(val options: GameOptions) {
    val active: Boolean = false
    private val players = mutableListOf<GamePlayer>()

    fun join(gamePlayer: GamePlayer): Boolean {
        return players.add(gamePlayer)
    }

    fun leave(gamePlayer: GamePlayer): Boolean {
        return players.remove(gamePlayer)
    }

    fun canStart(): Boolean {
        return players.size >= options.minPlayers
    }

    abstract fun start()

}
