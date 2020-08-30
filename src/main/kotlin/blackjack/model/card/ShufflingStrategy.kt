package blackjack.model.card

interface ShufflingStrategy {
    fun shuffle(cards: Set<Card>): Set<Card>
}
