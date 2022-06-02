package blackjack.model

class CardDeck private constructor(private var cards: Set<PlayingCard>) {
    fun draw(count: Int): Set<PlayingCard> {
        return take(count).andPutRemainingCards()
    }

    private fun take(count: Int): Set<PlayingCard> = cards.take(count).toSet()

    private fun Set<PlayingCard>.andPutRemainingCards(): Set<PlayingCard> = also { drawnCards ->
        cards = cards subtract drawnCards
    }

    companion object {
        fun create(): CardDeck {
            return CardDeck(PlayingCard.all().shuffled().toSet())
        }
    }
}
