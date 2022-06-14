package blackjack.domain.score

enum class CardScore(
    private val condition: (Score) -> Boolean,
    val profitRate: Double
) {
    NORMAL({ score -> score <= Score.BLACKJACK }, 1.0),
    BLACKJACK({ score -> score == Score.BLACKJACK }, 1.5),
    BUST({ score -> score > Score.BLACKJACK }, -1.0);

    companion object {
        fun of(score: Score, cardCount: Int): CardScore {
            if (cardCount == 2 && BLACKJACK.condition(score)) {
                return BLACKJACK
            }

            return values().filterNot { it == BLACKJACK }
                .find { it.condition(score) }
                ?: throw IllegalStateException("판별할 수 없는 카드 상태입니다. ($score)")
        }
    }
}
