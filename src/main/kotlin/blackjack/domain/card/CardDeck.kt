package blackjack.domain.card

import blackjack.domain.ExceptionTypes.WRONG_CARD_SIZE
import blackjack.domain.card.suit.CardSuit
import blackjack.domain.card.suit.SuitTypes

object CardDeck {
    private const val FULL_SIZE_OF_DECK = 52
    private val cardList: List<Card>

    init {
        val newCardList: MutableList<Card> = mutableListOf()
        SuitTypes.values()
            .map { createCards(it.types) }
            .forEach(newCardList::addAll)
        require(newCardList.size == FULL_SIZE_OF_DECK) { WRONG_CARD_SIZE }
        cardList = newCardList
    }

    fun getShuffledCardDeck() = ShuffledCardDeck(getShuffledCards())

    private fun getShuffledCards() = cardList.toMutableList().shuffled()

    private fun createCards(emblem: CardSuit) = (1..13).map { Card(emblem, it) }
}
