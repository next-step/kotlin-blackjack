package game.blackjack.domain

class Deck {
    val cards: ArrayDeque<Card> = ArrayDeque(allCards.shuffled())
    val size: Int get() = cards.size

    companion object {
        private val allCards: List<Card> = CardShape.values().flatMap { shape ->
            CardNumber.values().map { Card(it, shape) }
        }
    }
}
