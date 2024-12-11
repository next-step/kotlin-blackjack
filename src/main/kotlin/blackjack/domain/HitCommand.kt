package blackjack.domain

enum class HitCommand {
    Y,
    N, ;

    companion object {
        fun from(input: String): HitCommand {
            return entries.find { it.name.equals(input, ignoreCase = true) }
                ?: throw IllegalArgumentException(INVALID_INPUT)
        }

        private const val INVALID_INPUT = "입력값은 'y' 또는 'n'만 가능합니다."
    }
}
