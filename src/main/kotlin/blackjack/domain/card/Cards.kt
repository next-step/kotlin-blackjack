package blackjack.domain.card

import blackjack.domain.score.Score

@JvmInline
value class Cards(val cards: List<Card>) {
    init {
        require(cards.toSet().size == cards.size) { "중복된 카드를 가질 수 없습니다." }
    }

    fun getScore(): Score {
        var totalScore = cards.fold(Score.ZERO) { acc, card -> acc + card.toScore() }

        for (i in 1..getAceCount()) {
            totalScore = minusBustedAce(totalScore)
        }
        return totalScore
    }

    private fun minusBustedAce(totalScore: Score): Score {
        return if (totalScore.isBust()) {
            totalScore - Score.FOR_SECOND_ACE_VALUE
        } else totalScore
    }

    fun isBust(): Boolean {
        return getScore().isBust()
    }

    fun isBlackJack(): Boolean {
        return getScore().isBlackJack()
    }

    private fun getAceCount() = cards.filter { it.cardNumber == CardNumber.ACE }.size

    operator fun plus(card: Card): Cards {
        return Cards(cards + card)
    }

    operator fun plus(other: Cards): Cards {
        return Cards(this.cards + other.cards)
    }
}
