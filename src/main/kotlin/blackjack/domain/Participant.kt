package blackjack.domain

import blackjack.domain.card.Card

interface Participant {
    fun takeCard(card: Card)
    fun takeFirstTwoCards(cards: List<Card>)
    fun cardPointSum(): Int
}
