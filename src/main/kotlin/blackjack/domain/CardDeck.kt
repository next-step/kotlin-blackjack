package blackjack.domain

import blackjack.model.Card
import blackjack.model.DEFAULT_CARD_DECK

class CardDeck(
    initialCard: List<Card> = DEFAULT_CARD_DECK,
) : CardDeckPlay {

    override fun shuffle() {
        TODO("Not yet implemented")
    }

    override fun takeFirstCard(): Card {
        TODO("Not yet implemented")
    }
}

interface CardDeckPlay {
    fun shuffle()
    fun takeFirstCard(): Card
}
