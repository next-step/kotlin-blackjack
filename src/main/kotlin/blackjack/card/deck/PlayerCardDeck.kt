package blackjack.card.deck

import blackjack.card.Card

class PlayerCardDeck(cards: List<Card> = emptyList()) {

    private val cards: MutableList<Card> = copyCards(cards).toMutableList()

    fun getCards(): List<Card> {
        return copyCards(cards)
    }

    fun addCard(card: Card) {
        this.cards.add(card)
    }

    private fun copyCards(cards: List<Card>) = cards.map { Card(it.cardSignaturePack.copy()) }
}