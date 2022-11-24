package blackjack

class Player(
    val name: String,
    val playingCards: PlayingCards = PlayingCards(),
) {
    fun addCard(deal: Card) {
        playingCards.addOne(deal)
    }

    fun addCard(cards: MutableSet<Card>) {
        playingCards.addAll(cards)
    }
}
