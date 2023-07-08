package blackjack.domain

import blackjack.domain.card.Card

class Hand private constructor(val cards: List<Card>, val score: Score) {
    fun isBust() = score.isBust()

    fun add(card: Card): Hand {
        return Hand(
            cards = this.cards + card,
            score = score.plus(card.number.cardScore)
        )
    }

    fun add(cards: List<Card>): Hand {
        return Hand(
            cards = this.cards + cards,
            score = score.plus(cards.map { it.number.cardScore })
        )
    }

    companion object {
        val init = Hand(cards = emptyList(), score = Score.ZERO)
    }
}
