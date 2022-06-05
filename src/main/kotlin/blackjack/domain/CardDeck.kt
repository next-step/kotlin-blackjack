package blackjack.domain

import blackjack.exception.CardDeckIsEmptyException

class CardDeck(cards: List<Card>) : Deck {

    private val _cards: MutableList<Card> = cards.toMutableList()

    override fun draw() = if (_cards.isNotEmpty()) _cards.removeAt(0) else throw CardDeckIsEmptyException()
}
