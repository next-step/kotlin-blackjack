package blackjack.model

class CardDeck private constructor(private var cards: PlayingCards) {
    fun draw(count: Int): PlayingCards {
        val drawnCards = cards.take(count)
        cards = PlayingCards.from(cards.drop(count))

        return PlayingCards.from(drawnCards)
    }

    companion object {
        fun from(cards: PlayingCards): CardDeck {
            // Todo : distinct와 shuffled는 외부에서 하는 것으로
            return CardDeck(
                PlayingCards.from(cards.distinct().shuffled())
            )
        }
    }
}
