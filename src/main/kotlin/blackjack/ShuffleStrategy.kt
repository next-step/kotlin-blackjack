package blackjack

interface ShuffleStrategy {
    fun shuffle(cards: List<PlayingCard>): List<PlayingCard>
}
