package blackjack.model

class CardDeck private constructor(cards: List<PlayingCard>) {
    private val _cards: MutableList<PlayingCard> = cards.toMutableList()
    private val cards: List<PlayingCard>
        get() = _cards

    fun draw(count: Int): List<PlayingCard> {
        return cards.take(count).also { drawnCards ->
            _cards.removeAll(drawnCards)
        }
    }

    companion object {
        fun from(cards: List<PlayingCard>): CardDeck {
            // Todo : distinct와 shuffled는 외부에서 하는 것으로
            return CardDeck(cards.distinct().shuffled())
        }
    }
}
