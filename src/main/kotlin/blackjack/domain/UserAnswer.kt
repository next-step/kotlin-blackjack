package blackjack.domain

enum class UserAnswer(val answer: String) {
    YES("y"),
    NO("n");

    companion object {
        fun isValidAnswer(input: String): Boolean {
            return values().find { it.answer == input } != null
        }

        fun getUserAnswer(input: String): UserAnswer {
            return values().first { it.answer == input }
        }
    }
}
