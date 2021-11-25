package dev.zenqrt.game

import net.kyori.adventure.audience.Audience
import net.minestom.server.entity.Player

open class GamePlayer(val player: Player) : Audience by player {
    var currentGame: Game ?= null
}