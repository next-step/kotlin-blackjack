package blackjack.domain

import blackjack.enums.Denomination
import blackjack.enums.Suit
import java.util.LinkedList
import java.util.Queue

class GameDeck {
    private val deck: Queue<Card> = LinkedList()

    init {
        val shuffledDeck = mutableListOf<Card>()
        Denomination.values().forEach { denomination ->
            Suit.values().mapTo(shuffledDeck) { Card(denomination, it) }
        }
        shuffledDeck.shuffle()
        deck.addAll(shuffledDeck)
    }

    fun handOutCards(handOutCount: Int) : List<Card> {
        val handOutCards = mutableListOf<Card>()
        repeat(handOutCount) {
            handOutCards.add(deck.poll())
        }
        return handOutCards
    }

    fun handOutCard() : Card {
        return deck.poll()
    }
}
