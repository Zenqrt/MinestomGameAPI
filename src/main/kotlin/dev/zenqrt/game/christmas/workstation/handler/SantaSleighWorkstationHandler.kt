package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.ChristmasGamePlayer
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.leaderboard.LeaderboardPlayer
import dev.zenqrt.game.christmas.utils.isItem
import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Player
import net.minestom.server.sound.SoundEvent

class SantaSleighWorkstationHandler(private val game: ChristmasGame) : WorkstationHandler {
    override fun useStation(player: Player) {
        if(!player.itemInMainHand.isItem(Items.WRAPPED_PRESENT)) return

        consumeItemInHand(player)

        val gamePlayer = incrementToysBuilt(player, game.gamePlayers[player]!!)
        val leaderboardPlayer = LeaderboardPlayer(player, gamePlayer)

        println("Toys: ${gamePlayer.toysBuilt}")

        updateLeaderboard(leaderboardPlayer)
        updateSidebars()
        playSound(player)
    }

    private fun consumeItemInHand(player: Player) {
        player.itemInMainHand = player.itemInMainHand.withAmount { it - 1 }
    }
    
    private fun incrementToysBuilt(player: Player, gamePlayer: ChristmasGamePlayer): ChristmasGamePlayer = gamePlayer.copy(toysBuilt = gamePlayer.toysBuilt + 1).also {
        game.updatePlayer(it, player)
    }

    private fun updateLeaderboard(player: LeaderboardPlayer<ChristmasGamePlayer>) {
        game.leaderboard.updatePlayer(player)
    }

    private fun updateSidebars() {
        game.sidebarCreator.updateTopLeaderboard()
        game.leaderboard.leaderboard.forEach { updateSidebar(it) }
    }

    private fun updateSidebar(player: LeaderboardPlayer<ChristmasGamePlayer>) {
        val sidebar = game.sidebarCreator.buildGameSidebar(player)
        sidebar.addViewer(player.first)
    }

    private fun playSound(player: Player) {
        player.playSound(SOUND_EFFECT, Sound.Emitter.self())
    }

    companion object {
        val SOUND_EFFECT = Sound.sound(SoundEvent.ENTITY_PLAYER_LEVELUP, Sound.Source.MASTER, 100F, 2F)
    }
}