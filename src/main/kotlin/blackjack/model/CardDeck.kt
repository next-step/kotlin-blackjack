package blackjack.model

class CardDeck(cards: Collection<TrumpCard>) {

    private val cards: Collection<TrumpCard> = cards.toList()

    fun card(selector: (Collection<TrumpCard>) -> TrumpCard): TrumpCard {
        check(cards.isNotEmpty()) { "deck is empty" }
        return selector(cards)
    }

    operator fun minus(card: TrumpCard): CardDeck {
        require(card in cards) { "card must be in deck. deck($this), card($card)" }
        return CardDeck(cards - card)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardDeck

        return cards == other.cards
    }

    override fun hashCode(): Int {
        return cards.hashCode()
    }
}
