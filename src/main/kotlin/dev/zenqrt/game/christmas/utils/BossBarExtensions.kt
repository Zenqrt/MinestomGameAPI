package dev.zenqrt.game.christmas.utils

import net.kyori.adventure.bossbar.BossBar
import net.minestom.server.entity.Player
import world.cepi.kstom.Manager

val BossBar.viewers: MutableCollection<Player>
    get() = Manager.bossBar.getBossBarViewers(this)
