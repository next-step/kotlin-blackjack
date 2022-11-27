package blackjack

class Player(
    val name: String,
    val playingCards: PlayingCards = PlayingCards(),
) {
    fun addCard(deal: Card): Player {
        playingCards.addOne(deal)
        return this
    }

    fun addCard(cards: Set<Card>): Player {
        playingCards.addAll(cards)
        return this
    }
}
