package blackjack.domain

import blackjack.model.Card
import blackjack.model.DEFAULT_CARD_DECK

class CardDeck(
    initialCard: List<Card> = DEFAULT_CARD_DECK,
) : CardDeckPlay {

    private val _cards: MutableList<Card> =
        MutableList(initialCard.size) { index -> initialCard[index].copy() }

    override val cards: List<Card>
        get() = _cards.toList()

    override fun shuffle() {
        TODO("Not yet implemented")
    }

    override fun takeFirstCard(): Card {
        TODO("Not yet implemented")
    }
}

interface CardDeckPlay {
    val cards: List<Card>
    fun shuffle()
    fun takeFirstCard(): Card
}
