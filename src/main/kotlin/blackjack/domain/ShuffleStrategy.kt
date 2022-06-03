package blackjack.domain

interface ShuffleStrategy {
    fun shuffle(allCards: List<Card>): List<Card>
}

object RandomShuffleStrategy : ShuffleStrategy {
    override fun shuffle(allCards: List<Card>): List<Card> {
        return allCards.shuffled()
    }
}
