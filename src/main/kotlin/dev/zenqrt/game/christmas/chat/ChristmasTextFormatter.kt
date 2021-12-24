package dev.zenqrt.game.christmas.chat

import dev.zenqrt.game.api.chat.TextFormatter

class ChristmasTextFormatter : TextFormatter<String> {
    override fun formatMessage(message: String): String = "<gradient:${HexColor.TEXT_GRADIENT}>$message</gradient>"
    override fun formatUsername(username: String): String = "<aqua>$username</aqua>"
    override fun formatNumber(number: Number): String = formatValue(number.toString())
    override fun formatPrefix(prefix: String): String = "<color:#80b0ff>$prefix</color>"
    override fun formatValue(value: String): String = "<color:${HexColor.VALUE}>$value</color>"
}