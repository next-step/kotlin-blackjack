package blackjack.domain.card

class CardDeck(val cards: Cards = Cards(cardDeck.shuffled().toMutableList())) {
    fun getRandomCards(count: Int): Cards {
        val randomCards = cards.value.shuffled().take(count).toMutableList()
        cards.value.removeAll(randomCards)
        return Cards(randomCards)
    }

    companion object {
        val cardDeck = CardType.values().flatMap { type -> Denomination.values().map { denom -> Card(denom, type) } }
    }
}
