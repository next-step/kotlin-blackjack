package blackjack

data class Hand(
    val cards: List<Card> = listOf()
) {
    fun init(deck: Deck): Hand {
        return copy(cards = listOf(deck.hit(), deck.hit()))
    }

    fun hit(deck: Deck): Hand {
        val newCard = deck.hit()
        val newCards = cards + newCard
        return copy(cards = newCards)
    }
}
