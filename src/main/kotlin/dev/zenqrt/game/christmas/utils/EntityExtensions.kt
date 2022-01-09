package dev.zenqrt.game.christmas.utils

import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Entity
import net.minestom.server.instance.Instance
import java.util.concurrent.CompletableFuture

fun Entity.teleport(instance: Instance, pos: Pos): CompletableFuture<Void> = if(this.instance != instance) this.setInstance(instance, pos) else this.teleport(pos)