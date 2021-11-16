package blackjack.domain

enum class Command(private val command: String, val type: Boolean) {
    YES("y", true),
    NO("n", false);

    companion object {
        fun values(command: String): Command = values()
            .find { it.command == command.lowercase() }
            ?: throw IllegalArgumentException()
    }
}
