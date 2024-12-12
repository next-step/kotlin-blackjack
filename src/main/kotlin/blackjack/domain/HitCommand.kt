package blackjack.domain

enum class HitCommand {
    HIT,
    STAY, ;

    companion object {
        private val inputMapping =
            mapOf(
                "y" to HIT,
                "n" to STAY,
            )

        fun from(input: String): HitCommand {
            return inputMapping[input.lowercase()]
                ?: throw IllegalArgumentException(INVALID_INPUT)
        }

        private const val INVALID_INPUT = "유효하지 않은 입력입니다. 'y', 'n'를 입력해주세요."
    }
}
