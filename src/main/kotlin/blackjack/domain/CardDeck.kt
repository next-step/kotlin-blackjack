package blackjack.domain


class CardDeck(cards: List<Card>) : Deck {
    private val _cards: MutableList<Card> = cards.toMutableList()
    override fun draw(): Card = _cards.removeAt(0)
}


