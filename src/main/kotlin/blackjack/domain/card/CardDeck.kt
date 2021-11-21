package blackjack.domain.card

import blackjack.domain.ExceptionTypes.WRONG_CARD_SIZE
import blackjack.domain.card.emblem.CardEmblem
import blackjack.domain.card.emblem.EmblemTypes

object CardDeck {
    private val cardList: List<Card>
    private const val FULL_SIZE_OF_DECK = 52

    init {
        val newCardList: MutableList<Card> = mutableListOf()
        EmblemTypes.values()
            .map { createCards(it.types) }
            .forEach(newCardList::addAll)
        require(newCardList.size == FULL_SIZE_OF_DECK) { WRONG_CARD_SIZE }
        cardList = newCardList
    }

    fun getShuffledCardDeck() = ShuffledCardDeck(getShuffledCards())

    private fun getShuffledCards() = cardList.toMutableList().shuffled()

    private fun createCards(emblem: CardEmblem) = (1..13).map { Card(emblem, it) }
}
