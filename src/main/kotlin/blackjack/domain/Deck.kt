package blackjack.domain

import kotlin.random.Random

class Deck {

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

    fun shuffleDeck(deck: MutableList<Card>) {
        deck.shuffle(Random(System.currentTimeMillis()))
    }

    fun drawCard(): Card {
        require(deck.size > 0) { "남은 카드가 없습니다." }
        return deck.removeAt(deck.lastIndex)
    }
}
