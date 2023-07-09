package blackjack.domain

import blackjack.domain.card.Card

class Hand private constructor(val cards: List<Card>, val handScore: HandScore) {
    fun isBust() = handScore.isExceedsMax() && cards.size > 2

    fun isBlackJack() = handScore.isMax() && cards.size == 2

    fun add(card: Card): Hand {
        return Hand(
            cards = this.cards + card,
            handScore = handScore.plus(card.number.cardScore)
        )
    }

    fun add(cards: List<Card>): Hand {
        return Hand(
            cards = this.cards + cards,
            handScore = handScore.plus(cards.map { it.number.cardScore })
        )
    }

    companion object {
        val init = Hand(cards = emptyList(), handScore = HandScore.ZERO)
    }
}
