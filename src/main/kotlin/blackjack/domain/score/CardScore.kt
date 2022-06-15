package blackjack.domain.score

import blackjack.domain.blackjack.BlackJack

enum class CardScore(
    private val condition: (Score) -> Boolean,
    val profitRate: Double
) {
    NORMAL({ score -> score <= Score.BLACKJACK }, 1.0),
    BLACKJACK({ score -> score == Score.BLACKJACK }, 1.5),
    BUST({ score -> score > Score.BLACKJACK }, -1.0);

    companion object {
        fun of(score: Score, cardCount: Int): CardScore {
            if (cardCount == BlackJack.BASE_CARD_COUNT && BLACKJACK.condition(score)) {
                return BLACKJACK
            }

            return values().filterNot { it == BLACKJACK }
                .find { it.condition(score) }
                ?: throw IllegalStateException("판별할 수 없는 카드 상태입니다. ($score)")
        }
    }
}
