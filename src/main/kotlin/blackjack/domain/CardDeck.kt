package blackjack.domain

import blackjack.model.Card
import blackjack.model.DEFAULT_CARD_DECK

interface CardDeck {
    val cards: List<Card>
    val size: Int
    fun shuffle()
    fun takeOutFirstCard(): Card
}

class CardDeckImpl(
    initialCard: List<Card> = DEFAULT_CARD_DECK,
) : CardDeck {

    private val _cards: MutableList<Card> =
        MutableList(initialCard.size) { index -> initialCard[index].copy() }

    override val size: Int
        get() = _cards.size

    override val cards: List<Card>
        get() = _cards.toList()

    override fun shuffle() = _cards.shuffle()

    override fun takeOutFirstCard(): Card = _cards.removeFirst()
}

