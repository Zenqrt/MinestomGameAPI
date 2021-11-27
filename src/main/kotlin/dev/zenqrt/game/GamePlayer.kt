package dev.zenqrt.game

import net.kyori.adventure.audience.Audience
import net.minestom.server.entity.Player

open class GamePlayer(val player: Player, var currentGame: Game? = null) : Audience by player