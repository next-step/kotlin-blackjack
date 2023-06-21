package blackjack

class CardDeck(val cards: MutableList<Card> = cardDeck.shuffled().toMutableList()) {
    fun getRandomCards(count: Int): MutableList<Card> {
        val randomCards = cards.shuffled().take(count).toMutableList()
        cards.removeAll(randomCards)
        return randomCards
    }

    companion object {
        val cardDeck = CardType.values().flatMap { type -> Denomination.values().map { denom -> Card(denom, type) } }
    }
}
