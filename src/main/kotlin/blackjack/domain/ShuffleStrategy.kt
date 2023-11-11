package blackjack.domain

interface ShuffleStrategy {
    fun shuffle(cards: List<Card>): List<Card>
}

object RandomShuffleStrategy : ShuffleStrategy {
    override fun shuffle(cards: List<Card>): List<Card> = cards.shuffled()
}
