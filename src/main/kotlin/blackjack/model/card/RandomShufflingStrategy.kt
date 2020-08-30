package blackjack.model.card

object RandomShufflingStrategy : ShufflingStrategy {
    override fun shuffle(cards: Set<Card>): Set<Card> = cards.shuffled().toSet()
}
