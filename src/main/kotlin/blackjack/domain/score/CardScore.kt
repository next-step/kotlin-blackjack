package blackjack.domain.score

enum class CardScore(
    private val condition: (Score) -> Boolean
) {
    NORMAL({ score -> score < Score.BLACKJACK }),
    BLACKJACK({ score -> score == Score.BLACKJACK }),
    BUST({ score -> score > Score.BLACKJACK });

    companion object {
        fun of(score: Score): CardScore {
            return CardScore.values()
                .find { it.condition(score) }
                ?: throw IllegalStateException("판별할 수 없는 카드 상태입니다. ($score)")
        }
    }
}
