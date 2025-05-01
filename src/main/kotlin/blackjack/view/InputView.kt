package blackjack.view

import blackjack.domain.player.Player

object InputView {
    fun getPlayerNames(): List<String> {
        println("Enter the names of the players (comma-separated):")

        return readln()
            .split(",")
            .map { it.trim() }
            .filter { it.isNotBlank() }
    }

    fun getUserChoice(player: Player): Boolean {
        println("Would ${player.name.value} like to draw another card? (y for yes, n for no)")
        val input = readln()

        return UserChoice.from(input).value
    }
}

enum class UserChoice(val value: Boolean) {
    Y(true),
    N(false),
    ;

    companion object {
        fun from(input: String): UserChoice {
            return entries.find {
                it.name == input.uppercase()
            } ?: throw IllegalArgumentException("Invalid user choice: $input")
        }
    }
}
