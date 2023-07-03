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

    fun handOutCards(handOutCount: Int): List<Card> {
        val handOutCards = mutableListOf<Card>()
        repeat(handOutCount) {
            require(deck.isNotEmpty()) { "나눠 줄 카드가 존재하지 않습니다." }
            handOutCards.add(deck.poll())
        }
        return handOutCards
    }

    fun handOutCard(): Card {
        return deck.poll()
    }
}
