package dev.zenqrt.game.handler

import dev.zenqrt.game.timer.Endable
import dev.zenqrt.game.timer.Tickable

abstract class TickHandler : Tickable, Endable {
    internal var active = false
    abstract fun shouldStart(): Boolean
}