package blackjack

data class Hand(
    val cards: List<Card> = listOf()
) {
    fun init(deck: Deck): Hand {
        return copy(cards = listOf(deck.hit(), deck.hit()))
    }
}
