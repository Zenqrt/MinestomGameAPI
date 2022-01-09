package dev.zenqrt.game.christmas.commands

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.MinestomGameAPI
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType

class StartGameCommand : Command("startgame") {
    init {
        setDefaultExecutor { sender, _ -> sender.sendMessage("Provide a game id.") }

        val idArgument = ArgumentType.Integer("game_id")
        idArgument.setCallback { sender, _ -> sender.sendMessage("Please enter a valid integer.") }

        addSyntax({ sender, context ->
            val id = context[idArgument]
            val game = validateId(id, sender) ?: return@addSyntax
            startGame(game, sender)
        }, idArgument)
    }

    private fun validateId(id: Int, sender: CommandSender): Game<out GamePlayer>? {
        val game = MinestomGameAPI.gameRegistry.find(id)

        if(game == null) {
            sender.sendMessage("The game id $id does not exist!")
            return null
        }

        return game
    }

    private fun startGame(game: Game<out GamePlayer>, sender: CommandSender) {
        game.startGame()
        sender.sendMessage("Started game with the id ${game.id}!")
    }
}