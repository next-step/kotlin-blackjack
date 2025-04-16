package blackjack.view

object InputView {
    fun getPlayerNames(): List<String> {
        println("Enter the names of the players (comma-separated):")

        return readln()
            .split(",")
            .filter { it.isNotBlank() }
            .map { it.trim() }
    }

    fun getUserChoice(): UserChoice {
        println("Would pobi like to draw another card? (y for yes, n for no)")
        val input = readln()

        return UserChoice.from(input)
    }
}

enum class UserChoice {
    Y,
    N,
    ;

    companion object {
        fun from(input: String): UserChoice {
            return entries.find {
                it.name == input.uppercase()
            } ?: throw IllegalArgumentException("Invalid input: $input")
        }
    }
}
