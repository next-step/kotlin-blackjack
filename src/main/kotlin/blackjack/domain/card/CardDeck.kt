package blackjack.domain.card

class CardDeck {
    val cards: Cards = Cards(cardDeck.shuffled().toMutableList())
    fun getRandomCards(count: Int = 1): Cards {
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
