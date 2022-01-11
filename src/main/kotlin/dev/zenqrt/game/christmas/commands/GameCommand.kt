package dev.zenqrt.game.christmas.commands

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.MinestomGameAPI
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.kstom.command.arguments.delegate
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object GameCommand : Kommand({
    val start by literal
    val join by literal

    val id = ArgumentType.Integer("id")

    syntax(start, id) {
        val game = validateGame(context[id], sender) ?: return@syntax
        game.startGame()
        sender.sendMessage("Started game with the id ${game.id}!")
    }

    syntax(join, id).onlyPlayers {
        val game = validateGame(context[id], sender) ?: return@onlyPlayers
//        game.insertPlayer(game.createPlayer(player), player, game)
    }

}, "game")

private fun validateGame(id: Int, sender: CommandSender): Game<out GamePlayer>? {
    val game = MinestomGameAPI.gameRegistry.find(id)

    if(game == null) {
        sender.sendMessage("Game with id $id does not exist!")
        return null
    }

    return game
}