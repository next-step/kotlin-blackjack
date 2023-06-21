package blackjack.domain

import java.util.LinkedList
import java.util.Queue

data class Deck(private val cardList: Queue<Card>) {
    init {
        require(cardList.size > 0) { EMPTY_DECK_ERROR_MESSAGE }
    }

    companion object {
        private const val EMPTY_DECK_ERROR_MESSAGE = "덱이 비어있습니다"

        val DEFAULT_DECK: Deck by lazy {
            val list = LinkedList<Card>()
            for (suit in Suit.values()) {
                list.addSuitCards(suit)
            }
            Deck(list)
        }

        private fun MutableList<Card>.addSuitCards(suit: Suit) {
            for (number in CardNumber.NUMBER_RANGE) {
                add(Card(suit, CardNumber.of(number)))
            }
        }

        fun getShuffledDeck() = Deck(LinkedList(DEFAULT_DECK.cardList.shuffled()))
    }
}
