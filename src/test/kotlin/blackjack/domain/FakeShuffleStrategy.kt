package blackjack.domain

object FakeShuffleStrategy : ShuffleStrategy {

    override fun shuffle(cards: List<Card>): List<Card> {
        return cards.toList()
    }
}
