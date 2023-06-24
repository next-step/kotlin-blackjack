package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape

class Deck(private val deckShuffleStrategy: DeckShuffleStarategy) {

    private val deck: MutableList<Card>

    init {
        deck = createDeck()
        shuffleDeck(deck)
    }

    private fun createDeck(): MutableList<Card> {
        val deck = mutableListOf<Card>()
        for (shape in CardShape.values()) {
            for (number in CardNumber.values()) {
                deck.add(Card(shape, number))
            }
        }
        return deck
    }

    private fun shuffleDeck(deck: MutableList<Card>) {
        deckShuffleStrategy.shuffleDeck(deck)
    }

    fun drawCard(): Card {
        validateCardIsLeft()
        return deck.removeAt(deck.lastIndex)
    }

    fun drawTwoCard(): List<Card> {
        validateCardIsLeft()
        val twoCard = mutableListOf<Card>()
        repeat(2) {
            twoCard.add(deck.removeAt(deck.lastIndex))
        }
        return twoCard.toList()
    }

    private fun validateCardIsLeft() {
        require(deck.size > 0) { "남은 카드가 없습니다." }
    }
}
