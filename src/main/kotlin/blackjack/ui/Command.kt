package blackjack.ui

import blackjack.error.CommandNotFoundException

enum class Command(private val command: String, val isDrawable: Boolean) {
    YES("y", true),
    NO("n", false);

    companion object {
        fun values(command: String): Command = values()
            .find { it.command == command.lowercase() }
            ?: throw CommandNotFoundException(command)
    }
}
