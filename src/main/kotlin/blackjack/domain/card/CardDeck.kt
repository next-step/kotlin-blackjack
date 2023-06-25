package blackjack.domain.card

import blackjack.domain.BlackjackGame

class CardDeck {
    val cards: Cards = Cards(cardDeck.shuffled().toMutableList())
    fun getRandomCards(count: Int = BlackjackGame.DEAL_CARD_COUNT): Cards {
        val randomCards = cards.getRandomCard(count)
        cards.removeAllCards(randomCards)
        return randomCards
    }

    fun getRandomCard(): Card {
        return getRandomCards().getFirstCard()
    }

    companion object {
        val cardDeck = CardType.values().flatMap { type -> Denomination.values().map { denom -> Card(denom, type) } }
    }
}
