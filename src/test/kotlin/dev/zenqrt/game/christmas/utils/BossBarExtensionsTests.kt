package dev.zenqrt.game.christmas.utils

import dev.zenqrt.game.api.player.AccessibleFakePlayer
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer

class BossBarExtensionsTests : ShouldSpec({
    beforeSpec { MinecraftServer.init() }

    context("BossBar") {
        val bossBar = BossBar.bossBar(Component.empty(), 1F, BossBar.Color.RED, BossBar.Overlay.PROGRESS)
        val fakePlayer = AccessibleFakePlayer.create("test_player")

        should("contain fake player in bossBar viewers") {
            fakePlayer.showBossBar(bossBar)
            bossBar.viewers shouldContain fakePlayer
        }

        should("not contain fake player in bossBar viewers") {
            fakePlayer.hideBossBar(bossBar)
            bossBar.viewers shouldNotContain fakePlayer
        }

    }
})