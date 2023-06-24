package blackjack.domain.card

import blackjack.domain.score.Score

@JvmInline
value class Cards(val cards: List<Card>) {
    init {
        require(cards.toSet().size == cards.size) { "중복된 카드를 가질 수 없습니다." }
    }

    fun getScore(): Score {
        var totalScore = cards.fold(Score.ZERO) { acc, card -> acc + card.toScore() }

        if (totalScore.isBust() && hasAce()) {
            generateSequence(getAceCount()) { it - 1 }
                .takeWhile { it > 0 && totalScore > Score.BLACK_JACK }
                .forEach { _ ->
                    totalScore -= Score.FOR_SECOND_ACE_VALUE
                }
        }

        return totalScore
    }

    fun isBust(): Boolean {
        return getScore().isBust()
    }

    fun isBlackJack(): Boolean {
        return getScore().isBlackJack()
    }

    private fun hasAce() = cards.any { it.cardNumber == CardNumber.ACE }

    private fun getAceCount() = cards.filter { it.cardNumber == CardNumber.ACE }.size

    operator fun plus(card: Card): Cards {
        return Cards(cards + card)
    }
}
