package dev.zenqrt.game.christmas.commands

import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.registry.GameRegistryService
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.CommandData
import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.kstom.adventure.sendMiniMessage
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object GameCommand : Kommand({
    val create by literal
    val start by literal
    val join by literal

    val id = ArgumentType.Integer("id")

    syntax(create) {
        val game = ChristmasGame.create()
        GameRegistryService.register(game.id, game)

        context.returnData = CommandData()
            .set("id", game.id)
            .set("game", game)

        sender.sendMiniMessage("<green>Created game <yellow>${game.id}</yellow>!")
    }

    syntax(start, id) {
        executeGame(context[id], sender) { it.startGame() }
    }

    syntax(join, id).onlyPlayers {
        executeGame(context[id], sender) { it.insertPlayer(it.createPlayer(player), player, it) }
    }

}, "game")

private fun executeGame(id: Int, sender: CommandSender, handler: (ChristmasGame) -> Unit) {
    validateId(id, sender)?.let { handler(it) }
}

private fun validateId(id: Int, sender: CommandSender): ChristmasGame? {
    val game = GameRegistryService.find(id)

    if(game == null) {
        sender.sendMessage("The game id $id does not exist!")
        return null
    }

    return game
}