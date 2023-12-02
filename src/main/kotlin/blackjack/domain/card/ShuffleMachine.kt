package blackjack.domain.card

interface ShuffleMachine {
    fun shuffle(cards: List<Card>): List<Card>
}
