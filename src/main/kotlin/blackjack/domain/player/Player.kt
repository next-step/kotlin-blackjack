package blackjack.domain.player

import blackjack.domain.card.CardHold
import blackjack.domain.card.CardRank
import blackjack.domain.card.Deck
import blackjack.domain.rule.Score

sealed interface Player {
    val name: String
    val cardHold: CardHold

    fun getPoints(): Int {
        val sum = cardHold.getTotalPoints()
        if (cardHold.cards.any { card -> card.rank == CardRank.ACE } && sum <= THRESHOLD) {
            return sum + CardRank.ACE.getSpecialPoint()
        }
        return sum
    }

    fun canDraw(): Boolean = cardHold.getTotalPoints() <= THRESHOLD

    fun drawCard(deck: Deck) {
        if (!canDraw()) {
            throw IllegalArgumentException("카드 숫자의 합이 21을 초과합니다.")
        }

        val card = deck.draw()
        if (card != null) {
            cardHold.add(card)
        }
    }

    fun compareScore(other: Player): Score {
        if (this === other) {
            return Score()
        }

        return when {
            this.getPoints() > other.getPoints() -> Score(1, 0, 0)
            this.getPoints() < other.getPoints() -> Score(0, 0, 1)
            else -> Score(0, 1, 0)
        }
    }

    companion object {
        const val THRESHOLD = 21
    }
}
