package blackjack.domain.card

interface ShuffleStrategy {
    fun shuffle(cards: List<Card>): List<Card>
}
