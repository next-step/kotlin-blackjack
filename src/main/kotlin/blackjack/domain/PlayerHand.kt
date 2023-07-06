package blackjack.domain

import blackjack.domain.card.Card

class PlayerHand private constructor(val cards: List<Card>, val score: PlayerScore) {
    fun isBust() = score.isBust()

    fun add(card: Card): PlayerHand {
        return PlayerHand(
            cards = this.cards + card,
            score = score.plus(card.number.cardScore)
        )
    }

    fun add(cards: List<Card>): PlayerHand {
        return PlayerHand(
            cards = this.cards + cards,
            score = score.plus(cards.map { it.number.cardScore })
        )
    }

    companion object {
        val init = PlayerHand(cards = emptyList(), score = PlayerScore.ZERO)
    }
}
