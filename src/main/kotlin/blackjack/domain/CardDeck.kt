package blackjack.domain

import blackjack.model.Card
import blackjack.model.DEFAULT_CARD_DECK

class CardDeck(
    initialCard: List<Card> = DEFAULT_CARD_DECK,
) : CardDeckPlay {

    private val _cards: MutableList<Card> =
        MutableList(initialCard.size) { index -> initialCard[index].copy() }

    val size: Int
        get() = _cards.size

    override val cards: List<Card>
        get() = _cards.toList()

    override fun shuffle() = _cards.shuffle()

    override fun takeOutFirstCard(): Card = _cards.removeFirst()
}

interface CardDeckPlay {
    val cards: List<Card>
    fun shuffle()
    fun takeOutFirstCard(): Card
}
