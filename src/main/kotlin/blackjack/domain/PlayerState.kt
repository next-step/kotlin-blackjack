package blackjack.domain

import blackjack.domain.card.Card

class PlayerState private constructor(val cards: List<Card>, val score: PlayerScore) {
    fun canAddCard() = score.isMaxOrExceeded().not()

    fun add(card: Card): PlayerState {
        return PlayerState(
            cards = this.cards + card,
            score = score.add(card.number.cardScore)
        )
    }

    fun add(cards: List<Card>): PlayerState {
        return PlayerState(
            cards = this.cards + cards,
            score = score.add(cards.map { it.number.cardScore })
        )
    }

    companion object {
        val init = PlayerState(cards = emptyList(), score = PlayerScore.ZERO)
    }
}
