package blackjack.model

class CardDeck {

    val cards: MutableCollection<TrumpCard> = TrumpCard.ALL.toMutableList()

    fun draw(): TrumpCard {
        check(cards.isNotEmpty()) { "deck is empty" }
        return cards.random()
            .also { cards.remove(it) }
    }
}
