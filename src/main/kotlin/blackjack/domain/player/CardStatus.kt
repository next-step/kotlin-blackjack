package blackjack.domain.player

import blackjack.domain.Score

enum class CardStatus(
    private val condition: (Score) -> Boolean
) {
    NORMAL({ score -> score < Score.BLACKJACK }),
    BLACKJACK({ score -> score == Score.BLACKJACK }),
    BUST({ score -> score > Score.BLACKJACK });

    companion object {
        fun of(score: Score): CardStatus {
            return CardStatus.values()
                .find { it.condition(score) }
                ?: throw IllegalStateException("판별할 수 없는 카드 상태입니다. ($score)")
        }
    }
}
