package blackjack.domain.card

import blackjack.domain.ExceptionTypes
import blackjack.domain.ExceptionTypes.WRONG_CARD_SIZE
import blackjack.domain.card.suit.SuitTypes

class CardDeck {
    private val cardList: List<Card>
    private val shuffledCards: Iterator<Card>

    init {
        val newCardList = SuitTypes.values().flatMap { createCards(it) }
        require(newCardList.size == Companion.FULL_SIZE_OF_DECK) { WRONG_CARD_SIZE }
        cardList = newCardList
        shuffledCards = makeShuffledCardList().iterator()
    }

    fun getNextCard(): Card {
        require(shuffledCards.hasNext()) { ExceptionTypes.EMPTY_SHUFFLED_CARD_DECK }
        return shuffledCards.next()
    }

    private fun makeShuffledCardList(): List<Card> = cardList.toMutableList().shuffled()

    private fun createCards(emblem: SuitTypes) = (1..13).map { Card(emblem, it) }

    companion object {
        private const val FULL_SIZE_OF_DECK = 52
    }
}
