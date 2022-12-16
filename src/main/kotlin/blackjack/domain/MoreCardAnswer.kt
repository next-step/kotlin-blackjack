package blackjack.domain

enum class MoreCardAnswer(
    val answer: String
) {
    YES("y"),
    NO("n");

    companion object {
        private val ANSWER_MAP = MoreCardAnswer.values().associateBy { it.answer }

        fun from(value: String): MoreCardAnswer {
            val answer = ANSWER_MAP[value.lowercase()]
            require(answer != null) { "찾을 수 없는 응답값입니다. (입력:$answer)" }
            return answer
        }
    }
}
