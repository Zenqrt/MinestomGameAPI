package dev.zenqrt.game.api.chat

interface TextFormatter<F> {
    fun formatMessage(message: String): F
    fun formatUsername(username: String): F
    fun formatNumber(number: Number): F
    fun formatPrefix(prefix: String): F
    fun formatValue(value: String): F
}